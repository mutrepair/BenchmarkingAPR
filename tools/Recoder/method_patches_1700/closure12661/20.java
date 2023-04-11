  public JSType collapseUnion() {
if(isNativeObjectType()){
      return referencedType.collapseUnion();
    }
    return this;
  }