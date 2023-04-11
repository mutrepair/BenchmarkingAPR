  public JSType collapseUnion() {
if(this.matchesObjectContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }