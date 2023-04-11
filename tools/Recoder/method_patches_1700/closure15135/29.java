  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && 0)){      fnType.setPrototypeBasedOn(baseType);
    }
  }