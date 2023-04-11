  public JSType collapseUnion() {
if(isAllType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }