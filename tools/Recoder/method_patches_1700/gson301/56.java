  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if((entry && (entry.second == 0))){
        return entry.second;
      }
    }
    return null;
  }