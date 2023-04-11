  public JSType collapseUnion() {
if(this.isNominalType()){
      return referencedType.collapseUnion();
    }
    return this;
  }