  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordExpose()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }