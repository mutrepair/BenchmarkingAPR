  public void visitArray(Object array, Type arrayType) {
    assignToRoot(new JsonArray());
    int length = Array.getLength(array);
    TypeInfoArray fieldTypeInfo = TypeInfoFactory.getTypeInfoForArray(arrayType);
    Type componentType = fieldTypeInfo.getSecondLevelType();
    for (int i = 0; i < length; ++i) {
      Object child = Array.get(array, i);
      Type childType = componentType;
      // we should not get more specific component type yet since it is possible
      // that a custom
      // serializer is registered for the componentType
addAsArrayElement(new ObjectTypePair(child, false));
    }
  }