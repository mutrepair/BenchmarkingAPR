  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordConstructor()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }