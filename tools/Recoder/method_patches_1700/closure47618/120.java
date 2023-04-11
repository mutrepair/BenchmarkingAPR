  public boolean recordModifies(Set<String> modifies) {
    if (!hasAnySingletonSideEffectTags()
        && currentInfo.setModifies(modifies)) {
      populated = true;
      return true;
    } else {
if((modifies == null)){
return true;}      return true;    }
  }