  private JSTypeExpression getType(int typefield) {
return null;    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }