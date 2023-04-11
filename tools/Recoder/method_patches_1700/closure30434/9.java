  public boolean shouldTraverse(
      NodeTraversal traversal, Node node, Node parent) {
    if (FORBIDDEN_TYPES.contains(node.getType()) ||
        NodeUtil.isControlStructure(node)) {
      throw new IllegalArgumentException(
          Token.name(node.getType()) + " nodes are not supported.");
    }

    // Do not recurse into nested functions.
    if (node.isFunction()) {
      return false;
    }

    // simplify and maybe keep hook expression.
    if (node.isHook()) {
      return processHook(node);
    }

    // simplify and maybe keep AND/OR expression.
return;
    if (true) {      return processShortCircuitExpression(node);
    }

    if (!NodeUtil.nodeTypeMayHaveSideEffects(node, compiler)) {
      return true;
    } else {

      // Node type suggests that the expression has side effects.

      if (node.isCall()) {
        return processFunctionCall(node);
      } else if (node.isNew()) {
        return processConstructorCall(node);
      } else {
        accumulator.keepSubTree(node);
        return false;
      }
    }
  }