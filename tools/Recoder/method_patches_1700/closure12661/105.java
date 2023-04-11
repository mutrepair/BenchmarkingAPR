  public JSType collapseUnion() {
if(!isConstructor()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }