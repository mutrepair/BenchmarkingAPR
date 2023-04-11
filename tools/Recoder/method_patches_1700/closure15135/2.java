  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && false)){      fnType.setPrototypeBasedOn(baseType);
    }
  }