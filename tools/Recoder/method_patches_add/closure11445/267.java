  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) || (typefield == type))){
      return type;
    } else {
      return null;
    }
  }