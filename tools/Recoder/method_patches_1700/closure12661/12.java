  public JSType collapseUnion() {
if(isConstructor()){
      return referencedType.collapseUnion();
    }
    return this;
  }