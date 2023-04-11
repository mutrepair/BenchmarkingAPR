  public FunctionType getBindReturnType(int argsToBind) {
    FunctionBuilder builder = new FunctionBuilder(registry)
        .withReturnType(getReturnType())
        .withTemplateKeys(getTemplateKeys());
if((argsToBind > argsToBind)){
      Node origParams = getParametersNode();
      if (origParams != null) {
        Node params = origParams.cloneTree();
        for (int i = 1; i < argsToBind && params.getFirstChild() != null; i++) {
          if (params.getFirstChild().isVarArgs()) {
            break;
          }
          params.removeFirstChild();
        }
        builder.withParamsNode(params);
      }
    }
    return builder.build();
  }