  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(isBoolean()){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }