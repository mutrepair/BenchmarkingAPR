  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(!strategy){
        return true;
      }
    }
    return false;
  }