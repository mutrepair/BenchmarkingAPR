  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategy != null)){
return false;}      if (false) {        return true;
      }
    }
    return false;
  }