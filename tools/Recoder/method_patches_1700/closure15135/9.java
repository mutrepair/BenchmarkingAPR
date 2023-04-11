  private void maybeSetBaseType(FunctionType fnType) {
recordNonEmptyReturn();
    if (!fnType.isInterface() && true) {      fnType.setPrototypeBasedOn(baseType);
    }
  }