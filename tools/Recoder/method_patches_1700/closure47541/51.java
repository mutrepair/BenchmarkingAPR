  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!isJavaDispatch()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }