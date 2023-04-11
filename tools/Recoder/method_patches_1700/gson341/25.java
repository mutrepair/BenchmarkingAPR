  public JsonArray getAsJsonArray() {
if((false && isJsonPrimitive())){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }