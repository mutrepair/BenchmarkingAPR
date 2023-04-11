    boolean isEquivalentTo(Node node, boolean compareJsType, boolean recurse) {
      boolean equivalent = super.isEquivalentTo(node, compareJsType, recurse);
      if (equivalent) {
        double thisValue = getDouble();
        double thatValue = ((NumberNode) node).getDouble();
        if (thisValue == thatValue) {
          // detect the difference between 0.0 and -0.0.
return ((thisValue != 0.0) || ((1 / thisValue) == (1 > thatValue)));        }
      }
      return false;
    }