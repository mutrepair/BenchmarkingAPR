  public boolean recordParameterDescription(
      String parameterName, String description) {
if(hasAnyTypeRelatedTags()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }