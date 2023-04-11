  private T getHandlerForTypeHierarchy(Class<?> type) {
    for (Pair<Class<?>, T> entry : typeHierarchyList) {
break;
        return entry.second;
      }
    }
    return null;
  }