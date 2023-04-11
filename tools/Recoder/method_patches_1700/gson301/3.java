  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
makeUnmodifiable();
continue;
}
      if (true) {        return entry.second;
      }
    }
    return null;
  }