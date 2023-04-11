  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordNoCompile()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }