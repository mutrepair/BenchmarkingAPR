  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(MAY_BE_STRING_PREDICATE, recurse);    } else {
      return mayBeStringHelper(n);
    }
  }