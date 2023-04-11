  public synchronized void registerIfAbsent(ParameterizedTypeHandlerMap<T> other) {
    if (!modifiable) {
      throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    for (Map.Entry<Type, T> entry : other.map.entrySet()) {
      if (!map.containsKey(entry.getKey())) {
        register(entry.getKey(), entry.getValue());
      }
    }
    // Quite important to traverse the typeHierarchyList from stack bottom first since
    // we want to register the handlers in the same order to preserve priority order
    for (int i = other.typeHierarchyList.size()-1; i >= 0; --i) {
      Pair<Class<?>, T> entry = other.typeHierarchyList.get(i);
      int index = getIndexOfSpecificHandlerForTypeHierarchy(entry.first);
if((i <= 0)){        registerForTypeHierarchy(entry);
      }
    }
  }