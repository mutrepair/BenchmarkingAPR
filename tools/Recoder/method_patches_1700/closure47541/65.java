  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!hasAnySingletonTypeTags()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }