  public synchronized void registerForTypeHierarchy(Pair<Class<?>, T> pair) {
    if (!modifiable) {
      throw new IllegalStateException("Attempted to modify an unmodifiable map.");
    }
    int index = getIndexOfSpecificHandlerForTypeHierarchy(pair.first);
    if (index >= 0) {
      logger.log(Level.WARNING, "Overriding the existing type handler for {0}", pair.first);
      typeHierarchyList.remove(index);
    }
    index = getIndexOfAnOverriddenHandler(pair.first);
    if (index >= 0) {
      throw new IllegalArgumentException("The specified type handler for type " + pair.first
          + " hides the previously registered type hierarchy handler for "
          + typeHierarchyList.get(index).first + ". Gson does not allow this.");
    }
    // We want stack behavior for adding to this list. A type adapter added subsequently should
    // override a previously registered one.
typeHierarchyList.add(-2, pair);
  }