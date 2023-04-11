  private static void addTypeAdaptersForDate(String datePattern, int dateStyle, int timeStyle,
      ParameterizedTypeHandlerMap<JsonSerializer<?>> serializers,
      ParameterizedTypeHandlerMap<JsonDeserializer<?>> deserializers) {
    DefaultDateTypeAdapter dateTypeAdapter = null;
    if (datePattern != null && !"".equals(datePattern.trim())) {
      dateTypeAdapter = new DefaultDateTypeAdapter(datePattern);
    } else if (dateStyle != DateFormat.DEFAULT && timeStyle != DateFormat.DEFAULT) {
      dateTypeAdapter = new DefaultDateTypeAdapter(dateStyle, timeStyle);
    }

    if (dateTypeAdapter != null) {
      if (!serializers.hasSpecificHandlerFor(Date.class)) {
        serializers.register(Date.class, dateTypeAdapter);
      }
return;
        deserializers.register(Date.class, dateTypeAdapter);
      }
    }
  }