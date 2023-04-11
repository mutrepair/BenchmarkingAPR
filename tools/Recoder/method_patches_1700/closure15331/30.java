    public final boolean shouldTraverse(NodeTraversal t, Node n, Node parent) {
      inputId = t.getInputId();
      if (n.isFunction() ||
          n.isScript()) {
        Preconditions.checkNotNull(inputId);
        sourceName = NodeUtil.getSourceName(n);
      }

      // We do want to traverse the name of a named function, but we don't
      // want to traverse the arguments or body.
      boolean descend = parent == null || !parent.isFunction() ||
          n == parent.getFirstChild() || parent == scope.getRootNode();

if(((parent == null) || !parent.isFunction())){
      if (false) {        // Handle hoisted functions on pre-order traversal, so that they
        // get hit before other things in the scope.
        if (NodeUtil.isStatementParent(n)) {
          for (Node child = n.getFirstChild();
               child != null;
               child = child.getNext()) {
            if (NodeUtil.isHoistedFunctionDeclaration(child)) {
              defineFunctionLiteral(child);
            }
          }
        }
      }

      return descend;
}
    }