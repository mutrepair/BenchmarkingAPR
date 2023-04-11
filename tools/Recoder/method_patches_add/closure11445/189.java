  private JSTypeExpression getType(int typefield) {
if(isConstructor()){
return null;}    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }