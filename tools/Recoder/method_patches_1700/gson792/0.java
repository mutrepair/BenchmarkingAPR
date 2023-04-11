  public JsonElement toJsonTree(Object src, Type typeOfSrc) {
    if (src == null) {
      return JsonNull.createJsonNull();
    }
    JsonSerializationContextDefault context = new JsonSerializationContextDefault(
        createDefaultObjectNavigatorFactory(serializationStrategy), serializeNulls, serializers);
return context.serialize(src, typeOfSrc, true);  }