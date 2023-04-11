  private void maybeSetBaseType(FunctionType fnType) {
super.maybeSetBaseType(fnType);
    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }