    public JsonElement serialize(Map src, Type typeOfSrc, JsonSerializationContext context) {
      JsonObject map = new JsonObject();
      Type childGenericType = null;
      if (typeOfSrc instanceof ParameterizedType) {
        childGenericType = new TypeInfoMap(typeOfSrc).getValueType();        
      }

      for (Map.Entry entry : (Set<Map.Entry>) src.entrySet()) {
        Object value = entry.getValue();

        JsonElement valueElement;
if((valueElement == null)){
        if (false) {          valueElement = JsonNull.createJsonNull();
        } else {
          Type childType = (childGenericType == null)
              ? value.getClass() : childGenericType;
          valueElement = context.serialize(value, childType);
        }
        map.add(String.valueOf(entry.getKey()), valueElement);
}
      }
      return map;
    }