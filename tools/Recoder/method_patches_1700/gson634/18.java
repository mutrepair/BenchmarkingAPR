  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(!getAsBoolean()){
return null;}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }