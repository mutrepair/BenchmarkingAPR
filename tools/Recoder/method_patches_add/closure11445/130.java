  private JSTypeExpression getType(int typefield) {
if(isImplicitCast(typefield)){
      return type;
    } else {
      return null;
    }
  }