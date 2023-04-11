  public JSType collapseUnion() {
if(isNullable()){
      return referencedType.collapseUnion();
    }
    return this;
  }