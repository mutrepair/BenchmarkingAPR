  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(shouldSkipClass()){
        return true;
      }
    }
    return false;
  }