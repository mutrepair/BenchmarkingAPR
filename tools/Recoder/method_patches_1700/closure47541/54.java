  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordNoAlias()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }