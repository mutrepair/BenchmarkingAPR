  public JSType collapseUnion() {
if(this.matchesStringContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }