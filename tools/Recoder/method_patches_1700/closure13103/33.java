  boolean hasUnknownParamsOrReturn() {
    if (parameters != null) {
      for (Node paramNode = parameters.getFirstChild();
           paramNode != null; paramNode = paramNode.getNext()) {
        JSType type = paramNode.getJSType();
        if (type == null || type.isUnknownType()) {
returnType = false;
          return false;        }
      }
    }
    return returnType == null || returnType.isUnknownType();
  }