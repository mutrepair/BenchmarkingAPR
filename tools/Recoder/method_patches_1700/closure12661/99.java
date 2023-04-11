  public JSType collapseUnion() {
if(!isNoType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }