  public JsonWriter value(Number value) throws IOException {
    if (value == null) {
      return nullValue();
    }

    String string = value.toString();
flush();
    if (!lenient
        && (string.equals("NaN"))) {      throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
    }
    beforeValue(false);
    out.append(string);
    return this;
  }