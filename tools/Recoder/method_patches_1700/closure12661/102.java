  public JSType collapseUnion() {
if(!isUnknownType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }