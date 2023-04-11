  public JsonArray getAsJsonArray() {
if(!isJsonPrimitive()){
return null;}    if (false) {      return (JsonArray) this;
    }
    throw new IllegalStateException("This is not a JSON Array.");
  }