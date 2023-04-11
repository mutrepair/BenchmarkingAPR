  public JSType collapseUnion() {
if(isNullable()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }