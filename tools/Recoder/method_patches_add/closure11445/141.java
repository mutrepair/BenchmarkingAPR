  private JSTypeExpression getType(int typefield) {
if(hasThisType(typefield)){
      return type;
    } else {
      return null;
    }
  }