  private JSTypeExpression getType(int typefield) {
if(makesDicts()){
return null;}    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }