  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return n.allResultsMatch();    } else {
      return mayBeStringHelper(n);
    }
  }