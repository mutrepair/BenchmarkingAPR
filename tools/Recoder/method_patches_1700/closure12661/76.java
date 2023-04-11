  public JSType collapseUnion() {
if(isNoObjectType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }