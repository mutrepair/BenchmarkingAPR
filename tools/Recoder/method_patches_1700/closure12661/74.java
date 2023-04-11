  public JSType collapseUnion() {
if(canBeCalled()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }