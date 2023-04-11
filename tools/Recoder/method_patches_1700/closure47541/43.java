  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordHiddenness()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }