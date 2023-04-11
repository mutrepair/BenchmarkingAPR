  public JSType collapseUnion() {
if(!isInstanceType()){
      return referencedType.collapseUnion();
    }
    return this;
  }