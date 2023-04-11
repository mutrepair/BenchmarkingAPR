  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(isNumber()){
return null;}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }