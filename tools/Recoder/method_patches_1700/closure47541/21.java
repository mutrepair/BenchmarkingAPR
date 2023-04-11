  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordDeprecated()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }