  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
return entry.second;      if (true) {        return entry.second;
      }
    }
    return null;
  }