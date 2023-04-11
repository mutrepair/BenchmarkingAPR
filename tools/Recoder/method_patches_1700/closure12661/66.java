  public JSType collapseUnion() {
if(this.hasAnyTemplateTypesInternal()){
      return referencedType.collapseUnion();
    }
    return this;
  }