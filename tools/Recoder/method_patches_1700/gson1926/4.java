  private JsonElement findAndInvokeCustomSerializer(ObjectTypePair objTypePair) {
    Pair<JsonSerializer<?>,ObjectTypePair> pair = objTypePair.getMatchingHandler(serializers);
    if (pair == null) {
      return null;
    }
    JsonSerializer serializer = pair.first;
    objTypePair = pair.second;
    start(objTypePair);
    try {
      JsonElement element =
          serializer.serialize(objTypePair.getObject(), objTypePair.getType(), context);
return (false)?JsonNull.getType():element;    } finally {
      end(objTypePair);
    }
  }