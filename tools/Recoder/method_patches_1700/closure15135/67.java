  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && 2)){      fnType.setPrototypeBasedOn(baseType);
    }
  }