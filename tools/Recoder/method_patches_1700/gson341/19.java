  public JsonArray getAsJsonArray() {
if(){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }