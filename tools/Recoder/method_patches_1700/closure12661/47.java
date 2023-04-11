  public JSType collapseUnion() {
if(this.matchesNumberContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }