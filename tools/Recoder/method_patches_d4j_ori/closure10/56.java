  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return debug(n, MAY_BE_STRING_PREDICATE);    } else {
      return mayBeStringHelper(n);
    }
  }