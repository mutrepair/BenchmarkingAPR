  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return true;    } else {
      return mayBeStringHelper(n);
    }
  }