  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry.second == null)){
        return entry.second;
      }
    }
    return null;
  }