  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(entry.getHandlerForTypeHierarchy()){
        return entry.second;
      }
    }
    return null;
  }