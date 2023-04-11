  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
value = true;
    } else if (false) {      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }