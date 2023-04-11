  public JSType collapseUnion() {
if(hasReferenceName()){
      return referencedType.collapseUnion();
    }
    return this;
  }