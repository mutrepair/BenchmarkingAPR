  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
if(this.typeHierarchyList){
        return entry.second;
      }
    }
    return null;
  }