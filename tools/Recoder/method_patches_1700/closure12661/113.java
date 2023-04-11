  public JSType collapseUnion() {
if(!isNativeObjectType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }