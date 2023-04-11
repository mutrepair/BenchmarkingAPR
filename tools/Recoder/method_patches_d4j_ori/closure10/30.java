  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return visit(n);    } else {
      return mayBeStringHelper(n);
    }
  }