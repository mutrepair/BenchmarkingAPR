  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(strategy){
return false;}      if (false) {        return true;
      }
    }
    return false;
  }