  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && fnType.isInterface().mayHaveSingleThrow())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }