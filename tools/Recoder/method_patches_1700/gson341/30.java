  public JsonArray getAsJsonArray() {
if((JsonArray)this){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }