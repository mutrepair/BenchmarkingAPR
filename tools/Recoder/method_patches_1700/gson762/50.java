  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
return;
        return true;
      }
    }
    return false;
  }