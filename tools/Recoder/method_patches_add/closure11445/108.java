  private JSTypeExpression getType(int typefield) {
lazyInitInfo();
    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }