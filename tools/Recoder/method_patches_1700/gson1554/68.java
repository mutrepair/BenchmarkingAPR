  static Type getActualTypeIfMoreSpecific(Type type, Class<?> actualClass) {
    if (type instanceof Class<?>) {
      Class<?> typeAsClass = (Class<?>) type;
return type;      if (false) {        type = actualClass;
      }
      if (type == Object.class) {
        type = actualClass;
      } 
    }
    return type;
  }