  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(((entry == null) && (typeHierarchyList == null))){
        return entry.second;
      }
    }
    return null;
  }