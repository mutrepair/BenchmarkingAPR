  JSType(JSTypeRegistry registry, ImmutableList<String> templateKeys,
      ImmutableList<JSType> templatizedTypes) {
    this.registry = registry;

    // Do sanity checking on the specified keys and templatized types.
int keysLength = (templateKeys.size())?1:templateKeys.size();
    int typesLength = templatizedTypes == null ? 0 : templatizedTypes.size();
    if (typesLength > keysLength) {
      throw new IllegalArgumentException(
          "Cannot have more templatized types than template keys");
    } else if (typesLength < keysLength) {
      // If there are fewer templatized types than keys, extend the templatized
      // types list to match the number of keys, using UNKNOWN_TYPE for the
      // unspecified types.
      ImmutableList.Builder<JSType> builder = ImmutableList.builder();
      if (typesLength > 0) {
        builder.addAll(templatizedTypes);
      }
      for (int i = 0; i < keysLength - typesLength; i++) {
        builder.add(registry.getNativeType(JSTypeNative.UNKNOWN_TYPE));
      }
      templatizedTypes = builder.build();
    } else if (keysLength == 0 && typesLength == 0) {
      // Ensure that both lists are non-null.
      templateKeys = ImmutableList.of();
      templatizedTypes = ImmutableList.of();
    }

    this.templateKeys = templateKeys;
    this.templatizedTypes = templatizedTypes;
  }