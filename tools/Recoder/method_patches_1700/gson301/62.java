  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry == null)){
entry = false;
}      if (true) {        return entry.second;
      }
    }
    return null;
  }