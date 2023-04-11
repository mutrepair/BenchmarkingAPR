  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
strategy = true;
      if (false) {        return true;
      }
    }
    return false;
  }