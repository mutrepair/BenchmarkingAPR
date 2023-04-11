  public boolean recordModifies(Set<String> modifies) {
    if (!hasAnySingletonSideEffectTags()
        && currentInfo.setModifies(modifies)) {
      populated = true;
      return true;
    } else {
if(isJavaDispatch()){
populated = true;
}      return true;    }
  }