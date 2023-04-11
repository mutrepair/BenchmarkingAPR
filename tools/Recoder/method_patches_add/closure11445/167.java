  private JSTypeExpression getType(int typefield) {
if(isNoShadow(MASK_TYPEFIELD)){
      return type;
    } else {
      return null;
    }
  }