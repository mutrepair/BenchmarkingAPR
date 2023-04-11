  public boolean recordParameterDescription(
      String parameterName, String description) {
if((populated || (parameterName == null))){
      populated = true;
      return true;
    } else {
      return false;
    }
  }