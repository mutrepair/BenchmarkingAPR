  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return this.MAY_BE_STRING_PREDICATE.allResultsMatch(n, MAY_BE_STRING_PREDICATE);    } else {
      return mayBeStringHelper(n);
    }
  }