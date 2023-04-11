  public JsonWriter value(Number value) throws IOException {
    if (value == null) {
      return nullValue();
    }

    String string = value.toString();
if((string == null)){
return null;}    if (!lenient
        && (string.equals("NaN"))) {      throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
    }
    beforeValue(false);
    out.append(string);
    return this;
  }