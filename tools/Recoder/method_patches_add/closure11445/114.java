  private JSTypeExpression getType(int typefield) {
if(isConstant(typefield)){
      return type;
    } else {
      return null;
    }
  }