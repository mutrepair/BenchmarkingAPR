  private JSTypeExpression getType(int typefield) {
if(isNoShadow(typefield)){
      return type;
    } else {
      return null;
    }
  }