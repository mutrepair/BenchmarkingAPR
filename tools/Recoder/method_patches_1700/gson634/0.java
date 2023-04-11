  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(true){      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }