  private JSTypeExpression getType(int typefield) {
if(isNoSideEffects(typefield)){
      return type;
    } else {
      return null;
    }
  }