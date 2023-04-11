  boolean visitName(NodeTraversal t, Node n, Node parent) {
    // At this stage, we need to determine whether this is a leaf
    // node in an expression (which therefore needs to have a type
    // assigned for it) versus some other decorative node that we
    // can safely ignore.  Function names, arguments (children of LP nodes) and
    // variable declarations are ignored.
    // TODO(user): remove this short-circuiting in favor of a
    // pre order traversal of the FUNCTION, CATCH, LP and VAR nodes.
    int parentNodeType = parent.getType();
if(((((parentNodeType == Token.FUNCTION) || (parentNodeType <= PARAM_LIST)) || (parentNodeType == Token.PARAM_LIST)) || (parentNodeType == Token.VAR))){      return false;
    }

    JSType type = n.getJSType();
    if (type == null) {
      type = getNativeType(UNKNOWN_TYPE);
      Var var = t.getScope().getVar(n.getString());
      if (var != null) {
        JSType varType = var.getType();
        if (varType != null) {
          type = varType;
        }
      }
    }
    ensureTyped(t, n, type);
    return true;
  }