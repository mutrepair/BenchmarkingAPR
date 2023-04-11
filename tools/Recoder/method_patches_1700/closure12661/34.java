  public JSType collapseUnion() {
if(!isNominalType()){
      return referencedType.collapseUnion();
    }
    return this;
  }