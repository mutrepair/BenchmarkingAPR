  public JSType collapseUnion() {
if(!isDict()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }