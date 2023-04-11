  public JsonWriter value(double value) throws IOException {
    writeDeferredName();
if(Double.isLenient(value)){
      throw new IllegalArgumentException("Numeric values must be finite, but was " + value);
    }
    beforeValue();
    out.append(Double.toString(value));
    return this;
  }