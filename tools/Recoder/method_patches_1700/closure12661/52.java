  public JSType collapseUnion() {
if(this.isNoObjectType()){
      return referencedType.collapseUnion();
    }
    return this;
  }