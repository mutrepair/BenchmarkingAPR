  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && fnType.isInterface().mayBeFromExterns())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }