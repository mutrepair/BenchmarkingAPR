  public JsonArray getAsJsonArray() {
if((false && getAsBoolean())){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }