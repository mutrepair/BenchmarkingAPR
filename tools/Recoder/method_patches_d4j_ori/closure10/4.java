  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, recurse);    } else {
      return mayBeStringHelper(n);
    }
  }