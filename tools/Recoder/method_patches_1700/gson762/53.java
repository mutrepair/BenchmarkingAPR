  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(2){        return true;
      }
    }
    return false;
  }