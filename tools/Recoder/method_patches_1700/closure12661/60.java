  public JSType collapseUnion() {
if(this.isInterface()){
      return referencedType.collapseUnion();
    }
    return this;
  }