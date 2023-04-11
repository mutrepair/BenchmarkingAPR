  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && mayHaveNonEmptyReturns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }