  private void maybeSetBaseType(FunctionType fnType) {
if(!mayBeFromExterns()){
return;
}    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }