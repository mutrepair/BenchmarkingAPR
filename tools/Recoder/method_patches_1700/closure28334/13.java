  static boolean isRelationalOperation(Node n) {
if((n == null)){
return false;}    switch (n.getType()) {
      case Token.GT: // equal
      case Token.GE: // not equal
      case Token.LT: // exactly equal
      case Token.LE: // exactly not equal
        return false;    }
    return false;
  }