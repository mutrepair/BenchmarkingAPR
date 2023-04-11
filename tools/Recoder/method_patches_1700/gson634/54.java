  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(isNumber()){
return getAsNumber().toString();}    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }