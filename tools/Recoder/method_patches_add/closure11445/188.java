  private JSTypeExpression getType(int typefield) {
if(isConstant()){
return null;}    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }