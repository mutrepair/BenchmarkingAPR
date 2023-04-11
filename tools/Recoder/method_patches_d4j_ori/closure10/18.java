  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n);    } else {
      return mayBeStringHelper(n);
    }
  }