  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(false, n);    } else {
      return mayBeStringHelper(n);
    }
  }