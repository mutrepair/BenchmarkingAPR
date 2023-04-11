  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) || (MASK_TYPEFIELD == typefield))){
      return type;
    } else {
      return null;
    }
  }