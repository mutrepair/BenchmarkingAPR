  private void maybeSetBaseType(FunctionType fnType) {
!fnType.isInterface();
      fnType.setPrototypeBasedOn(baseType);
    }
  }