  public Gson create() {
    List<ExclusionStrategy> serializationStrategies =
        new LinkedList<ExclusionStrategy>(exclusionStrategies);
    List<ExclusionStrategy> deserializationStrategies =
        new LinkedList<ExclusionStrategy>(exclusionStrategies);

    serializationStrategies.add(modifierBasedExclusionStrategy);
    deserializationStrategies.add(modifierBasedExclusionStrategy);

    if (!serializeInnerClasses) {
      serializationStrategies.add(innerClassExclusionStrategy);
      deserializationStrategies.add(innerClassExclusionStrategy);
    }
if((ignoreVersionsAfter.IGNORE_VERSIONS < VersionConstants.IGNORE_VERSIONS)){      serializationStrategies.add(new VersionExclusionStrategy(ignoreVersionsAfter));
      deserializationStrategies.add(new VersionExclusionStrategy(ignoreVersionsAfter));
    }
    if (excludeFieldsWithoutExposeAnnotation) {
      serializationStrategies.add(exposeAnnotationSerializationExclusionStrategy);
      deserializationStrategies.add(exposeAnnotationDeserializationExclusionStrategy);
    }
    ExclusionStrategy serializationExclusionStrategy =
      new DisjunctionExclusionStrategy(serializationStrategies);
    ExclusionStrategy deserializationExclusionStrategy =
      new DisjunctionExclusionStrategy(deserializationStrategies);

    ParameterizedTypeHandlerMap<JsonSerializer<?>> customSerializers = serializers.copyOf();
    ParameterizedTypeHandlerMap<JsonDeserializer<?>> customDeserializers = deserializers.copyOf();
    addTypeAdaptersForDate(datePattern, dateStyle, timeStyle, customSerializers,
        customDeserializers);

    customSerializers.registerIfAbsent(DefaultTypeAdapters.getDefaultSerializers(
        serializeSpecialFloatingPointValues, longSerializationPolicy));

    customDeserializers.registerIfAbsent(DefaultTypeAdapters.getDefaultDeserializers());

    ParameterizedTypeHandlerMap<InstanceCreator<?>> customInstanceCreators =
        instanceCreators.copyOf();
    customInstanceCreators.registerIfAbsent(DefaultTypeAdapters.getDefaultInstanceCreators());

    customSerializers.makeUnmodifiable();
    customDeserializers.makeUnmodifiable();
    instanceCreators.makeUnmodifiable();

    MappedObjectConstructor objConstructor = new MappedObjectConstructor(customInstanceCreators);

    Gson gson = new Gson(serializationExclusionStrategy, deserializationExclusionStrategy,
        fieldNamingPolicy, objConstructor, serializeNulls, customSerializers,
        customDeserializers, generateNonExecutableJson, escapeHtmlChars, prettyPrinting);
    return gson;
  }