  public JSType collapseUnion() {
if(matchesObjectContext()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }