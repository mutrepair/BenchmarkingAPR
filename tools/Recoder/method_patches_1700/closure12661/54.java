  public JSType collapseUnion() {
if(this.isUnknownType()){
      return referencedType.collapseUnion();
    }
    return this;
  }