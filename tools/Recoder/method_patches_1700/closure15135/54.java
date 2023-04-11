  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() && -1)){      fnType.setPrototypeBasedOn(baseType);
    }
  }