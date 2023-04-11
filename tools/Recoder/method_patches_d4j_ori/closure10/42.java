  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return recurse;    } else {
      return mayBeStringHelper(n);
    }
  }