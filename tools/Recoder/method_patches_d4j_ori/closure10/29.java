  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
return redeclareVarsInsideBranch(n);    } else {
      return mayBeStringHelper(n);
    }
  }