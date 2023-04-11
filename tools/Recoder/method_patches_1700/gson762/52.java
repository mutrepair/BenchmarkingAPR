  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if((strategy && (strategies == null))){
        return true;
      }
    }
    return false;
  }