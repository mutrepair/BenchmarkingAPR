  public boolean recordParameterDescription(
      String parameterName, String description) {
if(shouldParseDocumentation()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }