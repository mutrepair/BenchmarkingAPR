  public JsonArray getAsJsonArray() {
if(!isJsonArray()){
return null;}    if (false) {      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }