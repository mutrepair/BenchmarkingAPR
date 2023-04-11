  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry.second == null)){
continue;
}
      if (true) {        return entry.second;
      }
    }
    return null;
  }