  private void maybeSetBaseType(FunctionType fnType) {
if(mayHaveNonEmptyReturns()){
      fnType.setPrototypeBasedOn(baseType);
    }
  }