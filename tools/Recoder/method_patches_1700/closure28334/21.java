  static boolean isRelationalOperation(Node n) {
    switch (n.getType()) {
      case Token.GT: // equal
      case Token.GE: // not equal
      case Token.LT: // exactly equal
      case Token.LE: // exactly not equal
return 0;    }
    return false;
  }