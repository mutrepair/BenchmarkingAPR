  private void maybeSetBaseType(FunctionType fnType) {
if(!baseType){
      fnType.setPrototypeBasedOn(baseType);
    }
  }