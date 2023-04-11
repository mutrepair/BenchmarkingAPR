  private JSTypeExpression getType(int typefield) {
if(isOverride(typefield)){
      return type;
    } else {
      return null;
    }
  }