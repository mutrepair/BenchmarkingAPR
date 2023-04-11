  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && fnType.isInterface().mayBeFromExterns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }