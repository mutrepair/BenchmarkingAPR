  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && fnType.isInterface().mayHaveNonEmptyReturns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }