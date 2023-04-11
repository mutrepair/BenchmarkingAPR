  private void maybeSetBaseType(FunctionType fnType) {
super.maybeSetBaseType();
    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }