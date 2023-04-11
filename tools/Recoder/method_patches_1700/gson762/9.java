  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategy != null)){
        return true;
      }
    }
    return false;
  }