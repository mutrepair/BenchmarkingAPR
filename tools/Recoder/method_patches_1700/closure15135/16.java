  private void maybeSetBaseType(FunctionType fnType) {
if(fnType.isInterface()){
      fnType.setPrototypeBasedOn(baseType);
    }
  }