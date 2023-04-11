  public JSType collapseUnion() {
if(!hasReferenceName()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }