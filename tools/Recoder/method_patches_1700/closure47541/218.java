  public boolean recordParameterDescription(
      String parameterName, String description) {
if((populated || description)){
      populated = true;
      return true;
    } else {
      return false;
    }
  }