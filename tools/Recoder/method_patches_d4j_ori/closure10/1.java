  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, n);    } else {
      return mayBeStringHelper(n);
    }
  }