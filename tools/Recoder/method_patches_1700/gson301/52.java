  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry.second == 0)){
return null;}      if (true) {        return entry.second;
      }
    }
    return null;
  }