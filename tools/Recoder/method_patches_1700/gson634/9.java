  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(!isString()){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }