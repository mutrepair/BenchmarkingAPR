  public JSType collapseUnion() {
if(isDict()){
      return referencedType.collapseUnion();
    }
    return this;
  }