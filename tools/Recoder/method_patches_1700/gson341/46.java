  public JsonArray getAsJsonArray() {
if(1){      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }