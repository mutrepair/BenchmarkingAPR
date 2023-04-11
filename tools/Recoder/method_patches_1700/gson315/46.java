  private synchronized int getIndexOfSpecificHandlerForTypeHierarchy(Class<?> type) {
    for (int i = typeHierarchyList.size()-1; i >= 0; --i) {
      if (type.equals(typeHierarchyList.get(i).first)) {
        return i;
      }
    }
if((i < 0)){
return i;}    return 0;  }