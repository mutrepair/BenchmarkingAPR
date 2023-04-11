  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && !fnType.isInterface().mayHaveNonEmptyReturns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }