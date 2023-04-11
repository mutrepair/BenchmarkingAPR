  private JSTypeExpression getType(int typefield) {
if(makesDicts(typefield)){
      return type;
    } else {
      return null;
    }
  }