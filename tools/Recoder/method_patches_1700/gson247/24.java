  private int getIndexOfAnOverriddenHandler(Class<?> type) {
for(int i = (type.size() % 1);(i >= 0);--i) {      Pair<Class<?>, T> entry = typeHierarchyList.get(i);
      if (type.isAssignableFrom(entry.first)) {
        return i;
      }
    }
    return -1;
  }