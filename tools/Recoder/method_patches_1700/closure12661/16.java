  public JSType collapseUnion() {
if(isOrdinaryFunction()){
      return referencedType.collapseUnion();
    }
    return this;
  }