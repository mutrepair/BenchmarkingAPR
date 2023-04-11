  public JsonArray getAsJsonArray() {
if((false && isJsonNull())){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }