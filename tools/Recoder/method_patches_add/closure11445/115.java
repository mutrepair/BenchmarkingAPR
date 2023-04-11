  private JSTypeExpression getType(int typefield) {
if(isConstructor(typefield)){
      return type;
    } else {
      return null;
    }
  }