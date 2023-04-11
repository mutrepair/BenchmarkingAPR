  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return n;    } else {
      return mayBeStringHelper(n);
    }
  }