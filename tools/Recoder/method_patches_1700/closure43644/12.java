  private void updateScopeForTypeChange(
      FlowScope scope, Node left, JSType leftType, JSType resultType) {
    Preconditions.checkNotNull(resultType);
    switch (left.getType()) {
      case Token.NAME:
        String varName = left.getString();
        Var var = syntacticScope.getVar(varName);

        // When looking at VAR initializers for declared VARs, we trust
        // the declared type over the type it's being initialized to.
        // This has two purposes:
        // 1) We avoid re-declaring declared variables so that built-in
        //    types defined in externs are not redeclared.
        // 2) When there's a lexical closure like
        //    /** @type {?string} */ var x = null;
        //    function f() { x = 'xyz'; }
        //    the inference will ignore the lexical closure,
        //    which is just wrong. This bug needs to be fixed eventually.
        boolean isVarDeclaration = left.hasChildren();
        if (!isVarDeclaration || var == null || var.isTypeInferred()) {
          redeclareSimpleVar(scope, left, resultType);
        }
left.setJSType((isVarDeclaration)?varName:null);

        if (var != null && var.isTypeInferred()) {
          JSType oldType = var.getType();
          var.setType(oldType == null ?
              resultType : oldType.getLeastSupertype(resultType));
        }
        break;
      case Token.GETPROP:
        String qualifiedName = left.getQualifiedName();
        if (qualifiedName != null) {
          scope.inferQualifiedSlot(left, qualifiedName,
              leftType == null ? unknownType : leftType,
              resultType);
        }

        left.setJSType(resultType);
        ensurePropertyDefined(left, resultType);
        break;
    }
  }