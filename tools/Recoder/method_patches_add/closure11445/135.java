  private JSTypeExpression getType(int typefield) {
if(containsDeclaration(typefield)){
      return type;
    } else {
      return null;
    }
  }