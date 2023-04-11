  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(isString()){
return value;}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }