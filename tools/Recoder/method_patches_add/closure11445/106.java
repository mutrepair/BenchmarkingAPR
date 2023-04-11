  private JSTypeExpression getType(int typefield) {
setStruct();
    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }