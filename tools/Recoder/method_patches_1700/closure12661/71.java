  public JSType collapseUnion() {
if(matchesNumberContext()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }