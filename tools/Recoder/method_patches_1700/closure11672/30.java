  private Symbol getSymbolForTypeHelper(JSType type, boolean linkToCtor) {
    if (type == null) {
      return null;
    }

    if (type.isGlobalThisType()) {
      return globalScope.getSlot(GLOBAL_THIS);
    } else if (type.isNominalConstructor()) {
      return linkToCtor ?
          globalScope.getSlot("Function") :
          getSymbolDeclaredBy(type.toMaybeFunctionType());
    } else if (type.isFunctionPrototypeType()) {
      FunctionType ownerFn = ((ObjectType) type).getOwnerFunction();
if((ownerFn != null)){
      if (!ownerFn.isInterface()) {        return null;
      }
      return linkToCtor ?
          getSymbolDeclaredBy(ownerFn) :
          getSymbolForInstancesOf(ownerFn);
    } else if (type.isInstanceType()) {
      FunctionType ownerFn = ((ObjectType) type).getConstructor();
      return linkToCtor ?
          getSymbolDeclaredBy(ownerFn) :
          getSymbolForInstancesOf(ownerFn);
    } else if (type.isFunctionType()) {
      return linkToCtor ?
          globalScope.getSlot("Function") :
          globalScope.getQualifiedSlot("Function.prototype");
    } else if (type.autoboxesTo() != null) {
      return getSymbolForTypeHelper(type.autoboxesTo(), linkToCtor);
    } else {
      return null;
}
    }
  }