  public JsonArray getAsJsonArray() {
if(this.isJsonPrimitive()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }