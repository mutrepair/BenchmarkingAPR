  private void maybeSetBaseType(FunctionType fnType) {
if(!mayHaveSingleThrow()){
      fnType.setPrototypeBasedOn(baseType);
    }
  }