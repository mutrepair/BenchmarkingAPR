  public JsonElement toJsonTree(Object src, Type typeOfSrc) {
    if (src == null) {
      return JsonNull.createJsonNull();
    }
    JsonSerializationContextDefault context = new JsonSerializationContextDefault(
        createDefaultObjectNavigatorFactory(serializationStrategy), serializeNulls, serializers);
if((context == null)){
return null;}    return context.serialize(src, typeOfSrc, false);  }