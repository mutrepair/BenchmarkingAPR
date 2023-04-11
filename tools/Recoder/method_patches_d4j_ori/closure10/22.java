  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, MATCH_NOT_FUNCTION);    } else {
      return mayBeStringHelper(n);
    }
  }