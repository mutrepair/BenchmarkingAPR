  public JsonArray getAsJsonArray() {
return this;    if (false) {      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }