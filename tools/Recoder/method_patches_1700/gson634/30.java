  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }