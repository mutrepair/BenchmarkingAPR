  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if(false){      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }