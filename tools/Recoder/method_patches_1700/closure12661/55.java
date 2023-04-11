  public JSType collapseUnion() {
if(this.isCheckedUnknownType()){
      return referencedType.collapseUnion();
    }
    return this;
  }