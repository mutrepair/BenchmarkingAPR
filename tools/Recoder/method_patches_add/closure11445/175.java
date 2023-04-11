  private JSTypeExpression getType(int typefield) {
if(lazyInitDocumentation(MASK_TYPEFIELD)){
      return type;
    } else {
      return null;
    }
  }