  public JSType collapseUnion() {
if(this.isNullable()){
      return referencedType.collapseUnion();
    }
    return this;
  }