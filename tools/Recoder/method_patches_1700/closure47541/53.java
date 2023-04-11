  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordOverride()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }