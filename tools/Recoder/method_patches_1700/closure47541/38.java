  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!isDescriptionRecorded()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }