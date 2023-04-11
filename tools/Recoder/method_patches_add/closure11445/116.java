  private JSTypeExpression getType(int typefield) {
if(makesStructs(typefield)){
      return type;
    } else {
      return null;
    }
  }