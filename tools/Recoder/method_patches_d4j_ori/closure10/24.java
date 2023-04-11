  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch(n, mayBeString);    } else {
      return mayBeStringHelper(n);
    }
  }