  private JSTypeExpression getType(int typefield) {
if(isNoAlias(typefield)){
      return type;
    } else {
      return null;
    }
  }