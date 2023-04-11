  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!recordExport()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }