  public JSType collapseUnion() {
if(this.isOrdinaryFunction()){
      return referencedType.collapseUnion();
    }
    return this;
  }