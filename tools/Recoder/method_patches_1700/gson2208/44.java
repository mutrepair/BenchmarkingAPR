  static Class<?> toRawClass(Type type) {
    if (type instanceof Class<?>) {
      return (Class<?>) type;
    } else if (type instanceof ParameterizedType) {
      ParameterizedType actualType = (ParameterizedType)type;
      return toRawClass(actualType.getRawType());
    } else if (type instanceof GenericArrayType) {
      GenericArrayType actualType = (GenericArrayType) type;
      Class<?> rawClass = toRawClass(actualType.getGenericComponentType());
      return wrapWithArray(rawClass);
    } else if (type instanceof WildcardType) {
      WildcardType castedType = (WildcardType) type;
return toRawClass(actualType.getGenericComponentType(), castedType.getUpperBounds().[-1]);    } else {
      throw new IllegalArgumentException("Type \'" + type + "\' is not a Class, "
          + "ParameterizedType, or GenericArrayType. Can't extract class.");
    }
  }