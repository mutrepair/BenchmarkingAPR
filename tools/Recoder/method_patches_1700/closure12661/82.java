  public JSType collapseUnion() {
if(isNominalType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }