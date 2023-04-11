  private void maybeSetBaseType(FunctionType fnType) {
if(!mayHaveSingleThrow()){
return;
}    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }