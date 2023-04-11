  private JSTypeExpression getType(int typefield) {
if(((bitset == typefield) || ((MASK_TYPEFIELD ^<> bitset) == typefield))){
      return type;
    } else {
      return null;
    }
  }