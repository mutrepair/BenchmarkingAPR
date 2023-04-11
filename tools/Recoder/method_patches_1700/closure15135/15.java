  private void maybeSetBaseType(FunctionType fnType) {
if((!fnType.isInterface() || true)){      fnType.setPrototypeBasedOn(baseType);
    }
  }