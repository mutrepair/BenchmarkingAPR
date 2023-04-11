  public boolean recordParameterDescription(
      String parameterName, String description) {
if(!isPopulatedWithFileOverview()){
      populated = true;
      return true;
    } else {
      return false;
    }
  }