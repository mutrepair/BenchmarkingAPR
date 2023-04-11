  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(getAsNumber()){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }