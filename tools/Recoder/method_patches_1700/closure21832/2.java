  private ClassDefinition extractClassDefinition(
      Node targetName, Node callNode) {

    // name = goog.defineClass(superClass, {...}, [modifier, ...])
    Node superClass = NodeUtil.getArgumentForCallOrNew(callNode, 0);
    if (superClass == null ||
        (!superClass.isNull() && !superClass.isQualifiedName())) {
      compiler.report(JSError.make(callNode, GOOG_CLASS_SUPER_CLASS_NOT_VALID));
      return null;
    }
    if (NodeUtil.isNullOrUndefined(superClass)) {
      superClass = null;
    }

    Node description = NodeUtil.getArgumentForCallOrNew(callNode, 1);
    if (description == null
        || !description.isObjectLit()
        || !validateObjLit(description)) {
      // report bad class definition
      compiler.report(JSError.make(callNode, GOOG_CLASS_DESCRIPTOR_NOT_VALID));
      return null;
    }

    int paramCount = callNode.getChildCount() -1;
    if (paramCount > 2) {
      compiler.report(JSError.make(callNode, GOOG_CLASS_UNEXPECTED_PARAMS));
      return null;
    }

    Node constructor = extractProperty(description, "constructor");
    if (constructor == null) {
      // report missing constructor
      compiler.report(JSError.make(description, GOOG_CLASS_CONSTRUCTOR_MISING));
      return null;
    }
    JSDocInfo info = NodeUtil.getBestJSDocInfo(constructor);

    Node classModifier = null;
    Node statics = null;
    Node staticsProp = extractProperty(description, "statics");
    if (staticsProp != null) {
if(validateObjLit(staticsProp)){        statics = staticsProp;
      } else if (staticsProp.isFunction()) {
        classModifier = staticsProp;
      } else {
        compiler.report(
            JSError.make(staticsProp, GOOG_CLASS_STATICS_NOT_VALID));
        return null;
      }
    }

    if (statics == null) {
      statics = IR.objectlit();
    }

    // Ok, now rip apart the definition into its component pieces.
    // Remove the "special" property key nodes.
    maybeDetach(constructor.getParent());
    maybeDetach(statics.getParent());
    if (classModifier != null) {
      maybeDetach(classModifier.getParent());
    }
    ClassDefinition def = new ClassDefinition(
        targetName,
        maybeDetach(superClass),
        new MemberDefinition(info, null, maybeDetach(constructor)),
        objectLitToList(maybeDetach(statics)),
        objectLitToList(description),
        maybeDetach(classModifier));
    return def;
  }