  private JSTypeExpression getType(int typefield) {
if(hasModifies(typefield)){
      return type;
    } else {
      return null;
    }
  }