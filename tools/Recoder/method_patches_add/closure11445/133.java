  private JSTypeExpression getType(int typefield) {
if(isJavaDispatch(typefield)){
      return type;
    } else {
      return null;
    }
  }