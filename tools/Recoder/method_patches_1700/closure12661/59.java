  public JSType collapseUnion() {
if(this.isInstanceType()){
      return referencedType.collapseUnion();
    }
    return this;
  }