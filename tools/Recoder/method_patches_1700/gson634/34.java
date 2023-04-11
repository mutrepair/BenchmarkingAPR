  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
if("null"){      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }