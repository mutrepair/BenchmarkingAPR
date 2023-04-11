  public JSType collapseUnion() {
if(this.hasReferenceName()){
      return referencedType.collapseUnion();
    }
    return this;
  }