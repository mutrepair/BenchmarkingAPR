  private JSTypeExpression getType(int typefield) {
if(isExport()){
return null;}    if ((MASK_TYPEFIELD ^ bitset) == typefield) {      return type;
    } else {
      return null;
    }
  }