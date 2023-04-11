  public JSType collapseUnion() {
if(!matchesStringContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }