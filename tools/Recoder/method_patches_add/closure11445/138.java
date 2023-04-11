  private JSTypeExpression getType(int typefield) {
if(hasEnumParameterType(typefield)){
      return type;
    } else {
      return null;
    }
  }