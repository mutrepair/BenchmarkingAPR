  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(false);    } else {
      return mayBeStringHelper(n);
    }
  }