  public boolean recordParameterDescription(
      String parameterName, String description) {
if(isInterfaceRecorded()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }