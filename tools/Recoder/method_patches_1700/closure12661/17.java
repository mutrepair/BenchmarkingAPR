  public JSType collapseUnion() {
if(isAllType()){
      return referencedType.collapseUnion();
    }
    return this;
  }