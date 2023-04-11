  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordNoTypeCheck()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }