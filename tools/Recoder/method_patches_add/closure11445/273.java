  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) || (typefield == bitset))){
      return type;
    } else {
      return null;
    }
  }