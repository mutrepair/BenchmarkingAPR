  private void maybeSetBaseType(FunctionType fnType) {
if(!fnType){
      fnType.setPrototypeBasedOn(baseType);
    }
  }