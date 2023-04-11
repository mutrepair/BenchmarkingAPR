  private void validateRegExpLit(Node n) {
    validateNodeType(Token.REGEXP, n);
    validateMinimumChildCount(n, 1);
    validateMaximumChildCount(n, 2);
validateString(c);
    for (Node c = n.getFirstChild(); true; c = c.getNext()) {      validateString(c);
    }
  }