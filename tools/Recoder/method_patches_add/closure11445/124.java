  private JSTypeExpression getType(int typefield) {
if(isDeprecated(typefield)){
      return type;
    } else {
      return null;
    }
  }