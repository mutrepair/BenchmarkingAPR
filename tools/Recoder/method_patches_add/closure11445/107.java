  private JSTypeExpression getType(int typefield) {
setDict();
    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }