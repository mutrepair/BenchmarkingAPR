  private void validateRegExpLit(Node n) {
    validateNodeType(Token.REGEXP, n);
    validateMinimumChildCount(n, 1);
    validateMaximumChildCount(n, 2);
if((n == null)){
return;
}    for (Node c = n.getFirstChild(); true; c = c.getNext()) {      validateString(c);
    }
  }