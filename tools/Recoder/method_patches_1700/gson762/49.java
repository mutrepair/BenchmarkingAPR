  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategies && (strategy == null))){
        return true;
      }
    }
    return false;
  }