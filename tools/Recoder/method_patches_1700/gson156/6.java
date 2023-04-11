  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers() {
return getDefaultInstanceCreators(true, LongSerializationPolicy.DEFAULT);  }