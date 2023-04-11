  public JSType collapseUnion() {
if(collapseUnion){
      return referencedType.collapseUnion();
    }
    return this;
  }