  private void maybeSetBaseType(FunctionType fnType) {
if(!mayHaveNonEmptyReturns()){
return;
}    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }