  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, debug);    } else {
      return mayBeStringHelper(n);
    }
  }