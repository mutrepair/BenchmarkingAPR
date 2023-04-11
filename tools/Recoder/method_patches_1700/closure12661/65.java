  public JSType collapseUnion() {
if(this.isNativeObjectType()){
      return referencedType.collapseUnion();
    }
    return this;
  }