  public boolean recordParameterDescription(
      String parameterName, String description) {
if((populated || (populated == null))){
      populated = true;
      return true;
    } else {
      return false;
    }
  }