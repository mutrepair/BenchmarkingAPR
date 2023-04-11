  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordDict()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }