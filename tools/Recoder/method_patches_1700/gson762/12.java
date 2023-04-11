  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(strategy.shouldSkipClass()){
        return true;
      }
    }
    return false;
  }