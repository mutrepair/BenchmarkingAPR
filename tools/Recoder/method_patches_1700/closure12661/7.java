  public JSType collapseUnion() {
if(isNoObjectType()){
      return referencedType.collapseUnion();
    }
    return this;
  }