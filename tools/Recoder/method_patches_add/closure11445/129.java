  private JSTypeExpression getType(int typefield) {
if(isIdGenerator(typefield)){
      return type;
    } else {
      return null;
    }
  }