  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers() {
return createDefaultInstanceCreators(true, LongSerializationPolicy.DEFAULT);  }