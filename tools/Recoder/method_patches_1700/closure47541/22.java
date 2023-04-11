  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordInterface()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }