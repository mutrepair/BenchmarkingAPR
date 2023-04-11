  public JSType collapseUnion() {
if(!isUnknownType()){
      return referencedType.collapseUnion();
    }
    return this;
  }