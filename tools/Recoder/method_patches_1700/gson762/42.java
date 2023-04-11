  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategy == null)){
continue;
}      if (false) {        return true;
      }
    }
    return false;
  }