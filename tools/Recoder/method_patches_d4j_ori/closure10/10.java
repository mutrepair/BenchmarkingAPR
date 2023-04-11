  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return allResultsMatch();    } else {
      return mayBeStringHelper(n);
    }
  }