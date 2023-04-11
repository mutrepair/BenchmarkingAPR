  private JSTypeExpression getType(int typefield) {
if(((typefield == MASK_TYPEFIELD) || ((MASK_TYPEFIELD ^<> bitset) == typefield))){
      return type;
    } else {
      return null;
    }
  }