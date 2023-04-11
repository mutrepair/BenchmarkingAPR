  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && !mayHaveSingleThrow())){
      fnType.setPrototypeBasedOn(baseType);
    }
  }