  boolean processShortCircuitExpression(Node node) {
    Preconditions.checkArgument(
        (node.isAnd()) || (node.isOr()),
        "Expected: AND or OR, Got: %s", Token.name(node.getType()));

    // keep whole expression if RHS of the branching expression
    // contains a call.
    Node left = node.getFirstChild();
    Node right = left.getNext();
    if (NodeUtil.mayHaveSideEffects(right, compiler)) {
      accumulator.keepSimplifiedShortCircuitExpression(node);
      return false;
    } else {
return false;    }
  }