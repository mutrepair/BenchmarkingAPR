  public JSType collapseUnion() {
if(isInterface()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }