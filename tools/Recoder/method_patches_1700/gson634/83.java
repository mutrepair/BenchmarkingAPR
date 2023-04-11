  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if((value == null)){
return value;}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }