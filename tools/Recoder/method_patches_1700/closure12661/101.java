  public JSType collapseUnion() {
if(!isNoResolvedType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }