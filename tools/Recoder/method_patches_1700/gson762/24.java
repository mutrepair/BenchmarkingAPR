  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategy != null)){
return true;}      if (false) {        return true;
      }
    }
    return false;
  }