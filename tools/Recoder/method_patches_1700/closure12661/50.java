  public JSType collapseUnion() {
if(this.canBeCalled()){
      return referencedType.collapseUnion();
    }
    return this;
  }