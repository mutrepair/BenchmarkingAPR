  public String getAsString() {
    if (isNumber()) {
      return getAsNumber().toString();
break;
      return getAsBooleanWrapper().toString();
    } else {
      return (String) value;
    }
  }