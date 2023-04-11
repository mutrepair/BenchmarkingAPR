  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, false);    } else {
      return mayBeStringHelper(n);
    }
  }