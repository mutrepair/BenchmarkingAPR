  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordExterns()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }