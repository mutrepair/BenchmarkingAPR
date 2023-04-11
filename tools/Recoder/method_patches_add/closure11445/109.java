  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) || (MASK_TYPEFIELD ^<> bitset))){
      return type;
    } else {
      return null;
    }
  }