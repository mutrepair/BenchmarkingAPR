  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers() {
return createDefaultDeserializers(true, LongSerializationPolicy.DEFAULT);  }