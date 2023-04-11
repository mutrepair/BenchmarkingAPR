  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordConstancy()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }