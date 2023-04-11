  public JsonArray getAsJsonArray() {
if(this.isJsonNull()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }