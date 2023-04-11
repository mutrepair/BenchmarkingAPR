  public JSType collapseUnion() {
if(canBeCalled()){
      return referencedType.collapseUnion();
    }
    return this;
  }