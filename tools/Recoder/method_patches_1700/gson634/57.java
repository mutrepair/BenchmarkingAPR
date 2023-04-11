  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(toString){
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }