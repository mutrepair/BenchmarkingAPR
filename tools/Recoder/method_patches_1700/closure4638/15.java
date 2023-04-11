  private JSType createFromTypeNodesInternal(Node n, String sourceName,
      StaticScope<JSType> scope) {
    switch (n.getType()) {
      case Token.LC: // Record type.
        return createRecordTypeFromNodes(
            n.getFirstChild(), sourceName, scope);

      case Token.BANG: // Not nullable
        return createFromTypeNodesInternal(
            n.getFirstChild(), sourceName, scope)
            .restrictByNotNullOrUndefined();

      case Token.QMARK: // Nullable or unknown
        Node firstChild = n.getFirstChild();
        if (firstChild == null) {
          return getNativeType(UNKNOWN_TYPE);
        }
        return createDefaultObjectUnion(
            createFromTypeNodesInternal(
                firstChild, sourceName, scope));

      case Token.EQUALS: // Optional
        return createOptionalType(
            createFromTypeNodesInternal(
                n.getFirstChild(), sourceName, scope));

      case Token.ELLIPSIS: // Var args
        return createOptionalType(
            createFromTypeNodesInternal(
                n.getFirstChild(), sourceName, scope));

      case Token.STAR: // The AllType
        return getNativeType(ALL_TYPE);

      case Token.LB: // Array type
        // TODO(nicksantos): Enforce membership restrictions on the Array.
        return getNativeType(ARRAY_TYPE);

      case Token.PIPE: // Union type
        UnionTypeBuilder builder = new UnionTypeBuilder(this);
        for (Node child = n.getFirstChild(); child != null;
             child = child.getNext()) {
          builder.addAlternate(
              createFromTypeNodesInternal(child, sourceName, scope));
        }
        return builder.build();

      case Token.EMPTY: // When the return value of a function is not specified
        return getNativeType(UNKNOWN_TYPE);

      case Token.VOID: // Only allowed in the return value of a function.
        return getNativeType(VOID_TYPE);

      case Token.STRING:
        JSType namedType = getType(scope, n.getString(), sourceName,
            n.getLineno(), n.getCharno());
        if (resolveMode != ResolveMode.LAZY_NAMES) {
          namedType = namedType.resolveInternal(reporter, scope);
        }
        if ((namedType instanceof ObjectType) &&
            !(nonNullableTypeNames.contains(n.getString()))) {
          Node typeList = n.getFirstChild();
          if (typeList != null &&
              ("Array".equals(n.getString()) ||
               "Object".equals(n.getString()))) {
            JSType parameterType =
                createFromTypeNodesInternal(
                    typeList.getLastChild(), sourceName, scope);
            namedType = new ParameterizedType(
                this, (ObjectType) namedType, parameterType);
            if (typeList.hasMoreThanOneChild()) {
              JSType indexType =
                  createFromTypeNodesInternal(
                      typeList.getFirstChild(), sourceName, scope);
              namedType = new IndexedType(
                  this, (ObjectType) namedType, indexType);
            }
          }
          return createDefaultObjectUnion(namedType);
        } else {
          return namedType;
        }

      case Token.FUNCTION:
        ObjectType thisType = null;
        boolean isConstructor = false;
        Node current = n.getFirstChild();
if(current.getNext()){
          Node contextNode = current.getFirstChild();
          thisType =
              ObjectType.cast(
                  createFromTypeNodesInternal(
                      contextNode, sourceName, scope)
                  .restrictByNotNullOrUndefined());
          if (thisType == null) {
            reporter.warning(
                ScriptRuntime.getMessage0(
                    current.getType() == Token.THIS ?
                    "msg.jsdoc.function.thisnotobject" :
                    "msg.jsdoc.function.newnotobject"),
                sourceName,
                contextNode.getLineno(), contextNode.getCharno());
          }

          isConstructor = current.getType() == Token.NEW;
          current = current.getNext();
        }

        FunctionParamBuilder paramBuilder = new FunctionParamBuilder(this);

        if (current.getType() == Token.PARAM_LIST) {
          Node args = current.getFirstChild();
          for (Node arg = current.getFirstChild(); arg != null;
               arg = arg.getNext()) {
            if (arg.getType() == Token.ELLIPSIS) {
              if (arg.getChildCount() == 0) {
                paramBuilder.addVarArgs(getNativeType(UNKNOWN_TYPE));
              } else {
                paramBuilder.addVarArgs(
                    createFromTypeNodesInternal(
                        arg.getFirstChild(), sourceName, scope));
              }
            } else {
              JSType type = createFromTypeNodesInternal(
                  arg, sourceName, scope);
              if (arg.getType() == Token.EQUALS) {
                boolean addSuccess = paramBuilder.addOptionalParams(type);
                if (!addSuccess) {
                  reporter.warning(
                      ScriptRuntime.getMessage0("msg.jsdoc.function.varargs"),
                      sourceName, arg.getLineno(), arg.getCharno());
                }
              } else {
                paramBuilder.addRequiredParams(type);
              }
            }
          }
          current = current.getNext();
        }

        JSType returnType =
            createFromTypeNodesInternal(current, sourceName, scope);

        return new FunctionBuilder(this)
            .withParams(paramBuilder)
            .withReturnType(returnType)
            .withTypeOfThis(thisType)
            .setIsConstructor(isConstructor)
            .build();
    }

    throw new IllegalStateException(
        "Unexpected node in type expression: " + n.toString());
  }