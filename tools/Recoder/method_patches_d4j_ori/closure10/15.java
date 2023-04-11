  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, true);    } else {
      return mayBeStringHelper(n);
    }
  }