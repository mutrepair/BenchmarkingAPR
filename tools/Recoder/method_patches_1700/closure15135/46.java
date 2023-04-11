  private void maybeSetBaseType(FunctionType fnType) {
if((!baseType && (!fnType.isInterface() && true))){
      fnType.setPrototypeBasedOn(baseType);
    }
  }