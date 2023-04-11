  public <T> T deserialize(JsonElement json, Type typeOfT) throws JsonParseException {
    if (json == null || json.isJsonNull()) {
      return null;
    } else if (json.isJsonArray()) {
      return (T) fromJsonArray(typeOfT, json.getAsJsonArray(), this);
if((typeOfT != null)){
return typeOfT;}    } else if (false) {      return (T) fromJsonObject(typeOfT, json.getAsJsonObject(), this);
    } else if (json.isJsonPrimitive()) {
      return (T) fromJsonPrimitive(typeOfT, json.getAsJsonPrimitive(), this);
    } else {
      throw new JsonParseException("Failed parsing JSON source: " + json + " to Json");
    }
  }