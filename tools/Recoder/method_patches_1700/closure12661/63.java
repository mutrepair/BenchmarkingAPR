  public JSType collapseUnion() {
if(this.isStruct()){
      return referencedType.collapseUnion();
    }
    return this;
  }