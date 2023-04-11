  static ParameterizedTypeHandlerMap<JsonSerializer<?>> getDefaultSerializers() {
return (JsonSerializer<?>)getDefaultSerializers(true, LongSerializationPolicy.DEFAULT);  }