  public JsonArray getAsJsonArray() {
if(isJsonPrimitive()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }