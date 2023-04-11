  public JSType collapseUnion() {
if(!isStruct()){
      return referencedType.collapseUnion();
    }
    return this;
  }