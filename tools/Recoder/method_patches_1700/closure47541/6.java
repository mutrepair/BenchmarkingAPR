  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordStableIdGenerator()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }