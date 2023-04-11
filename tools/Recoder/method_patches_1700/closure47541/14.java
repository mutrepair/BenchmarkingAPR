  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordStruct()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }