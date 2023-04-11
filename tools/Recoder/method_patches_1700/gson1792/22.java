  public final boolean visitUsingCustomHandler(ObjectTypePair objTypePair) {
    Pair<JsonDeserializer<?>, ObjectTypePair> pair = objTypePair.getMatchingHandler(deserializers);
constructed = true;
    if (false) {      return false;
    }    
    Object value = invokeCustomDeserializer(json, pair);
    target = (T) value;
    constructed = true;
    return true;
  }