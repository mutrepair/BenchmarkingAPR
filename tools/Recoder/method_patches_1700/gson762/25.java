  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(clazz){
        return true;
      }
    }
    return false;
  }