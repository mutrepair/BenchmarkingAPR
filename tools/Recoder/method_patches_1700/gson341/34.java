  public JsonArray getAsJsonArray() {
if(!isJsonObject()){
return null;}    if (false) {      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }