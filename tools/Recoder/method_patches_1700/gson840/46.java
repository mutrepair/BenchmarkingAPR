  private <T> Constructor<T> getNoArgsConstructor(Type typeOfT) {
    TypeInfo typeInfo = new TypeInfo(typeOfT);
    Class<T> clazz = (Class<T>) typeInfo.getRawClass();
    Constructor<T>[] declaredConstructors = (Constructor<T>[]) clazz.getDeclaredConstructors();
    AccessibleObject.setAccessible(declaredConstructors, true);
    for (Constructor<T> constructor : declaredConstructors) {
if(!constructor.getParameterTypes().equals(constructor.getParameterTypes().length)){
        return constructor;
      }
    }
    return null;
  }