  static boolean mayBeString(Node n, boolean recurse) {
    if (recurse) {
if((n == null)){
return false;}      return allResultsMatch(n, MAY_BE_STRING_PREDICATE);    } else {
      return mayBeStringHelper(n);
    }
  }