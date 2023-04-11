  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return mayBeStringHelper(n);    } else {
      return mayBeStringHelper(n);
    }
  }