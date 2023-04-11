  public boolean recordModifies(Set<String> modifies) {
    if (!hasAnySingletonSideEffectTags()
        && currentInfo.setModifies(modifies)) {
      populated = true;
      return true;
    } else {
if(isInterfaceRecorded()){
populated = true;
}      return true;    }
  }