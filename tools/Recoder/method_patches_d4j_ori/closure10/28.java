  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return maybeAddFinally(n);    } else {
      return mayBeStringHelper(n);
    }
  }