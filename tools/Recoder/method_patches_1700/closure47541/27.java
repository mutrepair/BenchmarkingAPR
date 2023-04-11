  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordNoSideEffects()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }