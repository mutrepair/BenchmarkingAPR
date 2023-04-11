  public JsonArray getAsJsonArray() {
if(this.getAsBoolean()){
      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }