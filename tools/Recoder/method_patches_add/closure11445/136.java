  private JSTypeExpression getType(int typefield) {
if(lazyInitDocumentation(typefield)){
      return type;
    } else {
      return null;
    }
  }