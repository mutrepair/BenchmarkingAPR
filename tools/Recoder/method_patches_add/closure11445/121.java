  private JSTypeExpression getType(int typefield) {
if(shouldPreserveTry(typefield)){
      return type;
    } else {
      return null;
    }
  }