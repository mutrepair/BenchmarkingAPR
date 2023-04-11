  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers() {
return getDefaultDeserializers(true, LongSerializationPolicy.DEFAULT);  }