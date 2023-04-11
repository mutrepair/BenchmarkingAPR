  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(value){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }