  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
return false;      if (false) {        return true;
      }
    }
    return false;
  }