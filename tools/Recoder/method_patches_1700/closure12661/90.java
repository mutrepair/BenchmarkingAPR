  public JSType collapseUnion() {
if(hasAnyTemplateTypesInternal()){
return null;}    if (true) {      return referencedType.collapseUnion();
    }
    return this;
  }