  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && !fnType.isInterface())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }