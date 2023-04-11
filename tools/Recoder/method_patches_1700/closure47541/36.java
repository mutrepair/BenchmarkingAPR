  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!isPopulated()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }