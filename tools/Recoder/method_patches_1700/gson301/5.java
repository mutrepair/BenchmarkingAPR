  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(!typeHierarchyList){
        return entry.second;
      }
    }
    return null;
  }