  public JSType collapseUnion() {
if(!isStruct()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }