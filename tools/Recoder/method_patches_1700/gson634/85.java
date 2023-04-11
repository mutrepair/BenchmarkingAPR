  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if((value == null)){
return getAsNumber().toString();}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }