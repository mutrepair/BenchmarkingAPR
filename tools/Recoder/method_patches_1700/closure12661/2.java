  public JSType collapseUnion() {
if(matchesNumberContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }