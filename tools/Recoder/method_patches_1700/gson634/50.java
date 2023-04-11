  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(!isNumber()){
return (String)value;}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }