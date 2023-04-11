  public boolean recordParameterDescription(
      String parameterName, String description) {
if(recordImplicitCast()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }