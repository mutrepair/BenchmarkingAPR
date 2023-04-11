  public JsonArray getAsJsonArray() {
if((false && isJsonObject())){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }