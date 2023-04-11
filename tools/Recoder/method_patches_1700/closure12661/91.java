  public JSType collapseUnion() {
if((false || referencedType.collapseUnion())){
      return referencedType.collapseUnion();
    }
    return this;
  }