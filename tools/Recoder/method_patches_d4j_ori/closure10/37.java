  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, null);    } else {
      return mayBeStringHelper(n);
    }
  }