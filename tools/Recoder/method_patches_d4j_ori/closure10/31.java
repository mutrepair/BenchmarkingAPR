  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(MAY_BE_STRING_PREDICATE, n, true);    } else {
      return mayBeStringHelper(n);
    }
  }