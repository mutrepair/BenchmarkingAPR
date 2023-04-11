  public JSType collapseUnion() {
if(matchesStringContext()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }