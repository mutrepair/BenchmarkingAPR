  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && fnType.isInterface().mayHaveSingleThrow())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }