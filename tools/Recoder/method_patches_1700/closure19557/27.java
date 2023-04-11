  private void validateRegExpLit(Node n) {
    validateNodeType(Token.REGEXP, n);
    validateMinimumChildCount(n, 1);
    validateMaximumChildCount(n, 2);
for(Node c = n.getFirstChild();0;c = c.getNext()) {      validateString(c);
    }
  }