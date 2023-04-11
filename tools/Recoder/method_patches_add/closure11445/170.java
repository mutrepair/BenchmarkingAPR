  private JSTypeExpression getType(int typefield) {
if(isNoSideEffects(MASK_TYPEFIELD)){
      return type;
    } else {
      return null;
    }
  }