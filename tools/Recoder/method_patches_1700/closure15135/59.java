  private void maybeSetBaseType(FunctionType fnType) {
if(((!fnType.isInterface() && true) && (baseType != true))){
      fnType.setPrototypeBasedOn(baseType);
    }
  }