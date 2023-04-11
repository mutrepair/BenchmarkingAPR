  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!isConstructorRecorded()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }