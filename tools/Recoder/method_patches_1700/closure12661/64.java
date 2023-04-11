  public JSType collapseUnion() {
if(this.isDict()){
      return referencedType.collapseUnion();
    }
    return this;
  }