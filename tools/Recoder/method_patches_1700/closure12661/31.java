  public JSType collapseUnion() {
if(!isCheckedUnknownType()){
      return referencedType.collapseUnion();
    }
    return this;
  }