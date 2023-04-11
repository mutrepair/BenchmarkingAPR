  public JSType collapseUnion() {
if(this.isNoResolvedType()){
      return referencedType.collapseUnion();
    }
    return this;
  }