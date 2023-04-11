  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) == typefield)){
      return type;
    } else {
      return null;
    }
  }