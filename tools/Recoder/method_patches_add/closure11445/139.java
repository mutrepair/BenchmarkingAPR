  private JSTypeExpression getType(int typefield) {
if(hasTypedefType(typefield)){
      return type;
    } else {
      return null;
    }
  }