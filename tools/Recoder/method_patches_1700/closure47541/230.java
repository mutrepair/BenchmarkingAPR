  public boolean recordParameterDescription(
      String parameterName, String description) {
if((populated && (description == null))){
      populated = true;
      return true;
    } else {
      return false;
    }
  }