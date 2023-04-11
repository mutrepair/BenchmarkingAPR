  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(!getAsBooleanWrapper){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }