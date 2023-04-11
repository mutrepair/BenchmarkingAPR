  public JSType collapseUnion() {
if(!isInterface()){
      return referencedType.collapseUnion();
    }
    return this;
  }