  public JSType collapseUnion() {
if(this.isNoType()){
      return referencedType.collapseUnion();
    }
    return this;
  }