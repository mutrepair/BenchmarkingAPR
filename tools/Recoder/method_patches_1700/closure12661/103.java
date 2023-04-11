  public JSType collapseUnion() {
if(!isCheckedUnknownType()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }