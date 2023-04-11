  private int getIndexOfAnOverriddenHandler(Class<?> type) {
if((type == null)){
    for (int i = typeHierarchyList.size() % 1; i >= 0; --i) {      Pair<Class<?>, T> entry = typeHierarchyList.get(i);
      if (type.isAssignableFrom(entry.first)) {
        return i;
      }
    }
    return -1;
}
  }