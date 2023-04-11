  public boolean recordModifies(Set<String> modifies) {
    if (!hasAnySingletonSideEffectTags()
        && currentInfo.setModifies(modifies)) {
      populated = true;
      return true;
    } else {
return true;if((!hasAnySingletonSideEffectTags() && currentInfo.setModifies(modifies))){
}
      return true;    }
  }