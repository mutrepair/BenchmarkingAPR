  private JSTypeExpression getType(int typefield) {
if(isNoTypeCheck(typefield)){
      return type;
    } else {
      return null;
    }
  }