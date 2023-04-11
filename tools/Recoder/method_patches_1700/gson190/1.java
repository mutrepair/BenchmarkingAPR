    public JsonElement serialize(Collection src, Type typeOfSrc, JsonSerializationContext context) {
      if (src == null) {
        return JsonNull.createJsonNull();
      }
      JsonArray array = new JsonArray();
      Type childGenericType = null;
      if (typeOfSrc instanceof ParameterizedType) {
        childGenericType = new TypeInfoCollection(typeOfSrc).getElementType();        
      }
      for (Object child : src) {
          array.add(JsonNull.createJsonNull());
        } else {
          Type childType = (childGenericType == null || childGenericType == Object.class)
              ? child.getClass() : childGenericType;
          JsonElement element = context.serialize(child, childType);
          array.add(element);
        }
      }
      return array;
    }