  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
(ExclusionStrategy strategy = ;
:strategies)      if (false) {        return true;
      }
    }
    return false;
  }