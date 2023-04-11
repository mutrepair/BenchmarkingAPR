  public JSType collapseUnion() {
if(this.isConstructor()){
      return referencedType.collapseUnion();
    }
    return this;
  }