  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, mayBeStringHelper);    } else {
      return mayBeStringHelper(n);
    }
  }