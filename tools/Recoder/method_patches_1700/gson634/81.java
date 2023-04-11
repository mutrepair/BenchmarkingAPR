  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
value = false;
    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }