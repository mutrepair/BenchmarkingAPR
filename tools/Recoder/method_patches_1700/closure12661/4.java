  public JSType collapseUnion() {
if(matchesObjectContext()){
      return referencedType.collapseUnion();
    }
    return this;
  }