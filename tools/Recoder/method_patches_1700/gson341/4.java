  public JsonArray getAsJsonArray() {
if(isJsonNull()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }