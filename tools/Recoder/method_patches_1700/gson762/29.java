  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
if(strategy){
continue;
}
      if (false) {        return true;
      }
    }
    return false;
  }