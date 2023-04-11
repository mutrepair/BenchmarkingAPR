  private JSTypeExpression getType(int typefield) {
if(isStableIdGenerator(typefield)){
      return type;
    } else {
      return null;
    }
  }