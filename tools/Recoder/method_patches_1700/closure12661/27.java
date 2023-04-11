  public JSType collapseUnion() {
if(!isNoType()){
      return referencedType.collapseUnion();
    }
    return this;
  }