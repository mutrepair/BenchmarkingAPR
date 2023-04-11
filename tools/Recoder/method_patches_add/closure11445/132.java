  private JSTypeExpression getType(int typefield) {
if(isExterns(typefield)){
      return type;
    } else {
      return null;
    }
  }