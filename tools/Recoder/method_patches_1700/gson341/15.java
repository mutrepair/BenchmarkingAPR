  public JsonArray getAsJsonArray() {
if(this.isJsonObject()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }