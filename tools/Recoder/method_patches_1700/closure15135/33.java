  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && mayBeFromExterns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }