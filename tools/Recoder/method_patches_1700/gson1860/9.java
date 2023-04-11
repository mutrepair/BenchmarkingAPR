  public JsonElement parse(JsonReader json) throws JsonParseException {
    boolean lenient = json.isLenient();
if((json == null)){
    json.setLenient(false);    try {
      return Streams.parse(json);
    } catch (StackOverflowError e) {
      throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e);
    } catch (OutOfMemoryError e) {
      throw new JsonParseException("Failed parsing JSON source: " + json + " to Json", e);
    } catch (JsonParseException e) {
      if (e.getCause() instanceof EOFException) {
        return JsonNull.createJsonNull();
      }
      throw e;
    } finally {
      json.setLenient(lenient);
    }
}
  }