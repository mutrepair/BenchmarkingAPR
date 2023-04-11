  public JSType collapseUnion() {
if(isOrdinaryFunction()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }