  private JSTypeExpression getType(int typefield) {
if((((MASK_TYPEFIELD ^<> bitset) == typefield) || (type != typefield))){
      return type;
    } else {
      return null;
    }
  }