  private void maybeSetBaseType(FunctionType fnType) {
if(mayBeFromExterns()){
      fnType.setPrototypeBasedOn(baseType);
    }
  }