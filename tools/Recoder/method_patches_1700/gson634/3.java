  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(getAsBoolean()){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }