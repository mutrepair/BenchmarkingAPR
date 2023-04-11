  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(allResultsMatch(n, MAY_BE_STRING_PREDICATE));    } else {
      return mayBeStringHelper(n);
    }
  }