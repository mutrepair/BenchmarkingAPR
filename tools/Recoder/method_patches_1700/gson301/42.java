  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry != null)){
return entry.second;}      if (true) {        return entry.second;
      }
    }
    return null;
  }