  public JsonArray getAsJsonArray() {
if(!isJsonObject()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }