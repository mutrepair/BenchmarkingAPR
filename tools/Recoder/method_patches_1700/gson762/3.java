  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(strategy.isEmpty()){
        return true;
      }
    }
    return false;
  }