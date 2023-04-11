  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(isNumber()){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }