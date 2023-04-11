  public JSType collapseUnion() {
if(this.isAllType()){
      return referencedType.collapseUnion();
    }
    return this;
  }