  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return false;    } else {
      return mayBeStringHelper(n);
    }
  }