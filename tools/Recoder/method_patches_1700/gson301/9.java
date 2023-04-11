  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(entry.equals()){
        return entry.second;
      }
    }
    return null;
  }