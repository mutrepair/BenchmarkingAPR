  public JSType collapseUnion() {
if(!isNoResolvedType()){
      return referencedType.collapseUnion();
    }
    return this;
  }