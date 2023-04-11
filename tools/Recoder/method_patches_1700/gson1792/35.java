  public final boolean visitUsingCustomHandler(ObjectTypePair objTypePair) {
    Pair<JsonDeserializer<?>, ObjectTypePair> pair = objTypePair.getMatchingHandler(deserializers);
pair = objTypePair;
    if (false) {      return false;
    }    
    Object value = invokeCustomDeserializer(json, pair);
    target = (T) value;
    constructed = true;
    return true;
  }