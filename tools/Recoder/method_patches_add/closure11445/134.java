  private JSTypeExpression getType(int typefield) {
if(isNoCompile(typefield)){
      return type;
    } else {
      return null;
    }
  }