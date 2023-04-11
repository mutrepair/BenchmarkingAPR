  private JSTypeExpression getType(int typefield) {
if(isNoSideEffects()){
      return type;
    } else {
      return null;
    }
  }