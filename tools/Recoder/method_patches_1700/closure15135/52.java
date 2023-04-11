  private void maybeSetBaseType(FunctionType fnType) {
if(!isInterface()){
      fnType.setPrototypeBasedOn(baseType);
    }
  }