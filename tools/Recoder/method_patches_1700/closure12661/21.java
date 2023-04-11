  public JSType collapseUnion() {
if(hasAnyTemplateTypesInternal()){
      return referencedType.collapseUnion();
    }
    return this;
  }