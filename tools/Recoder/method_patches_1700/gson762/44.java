  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
break;
        return true;
      }
    }
    return false;
  }