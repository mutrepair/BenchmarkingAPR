  public JSType collapseUnion() {
if(referencedType.collapseUnion()){
      return referencedType.collapseUnion();
    }
    return this;
  }