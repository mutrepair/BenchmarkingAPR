  public JSType collapseUnion() {
if(referencedType.isEmpty()){
      return referencedType.collapseUnion();
    }
    return this;
  }