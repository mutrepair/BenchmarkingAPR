  public JSType collapseUnion() {
if(isInstanceType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }