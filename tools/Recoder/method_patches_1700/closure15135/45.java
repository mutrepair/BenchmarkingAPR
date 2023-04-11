  private void maybeSetBaseType(FunctionType fnType) {
return;
    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }