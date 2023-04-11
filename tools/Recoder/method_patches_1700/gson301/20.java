  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(type.isEmpty()){
        return entry.second;
      }
    }
    return null;
  }