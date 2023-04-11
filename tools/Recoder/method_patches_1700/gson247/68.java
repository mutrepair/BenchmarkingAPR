  private int getIndexOfAnOverriddenHandler(Class<?> type) {
(;;)      Pair<Class<?>, T> entry = typeHierarchyList.get(i);
      if (type.isAssignableFrom(entry.first)) {
        return i;
      }
    }
    return -1;
  }