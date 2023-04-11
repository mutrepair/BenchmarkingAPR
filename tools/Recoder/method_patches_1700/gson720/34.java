  private static boolean isIntegral(JsonPrimitive primitive) {
    if (primitive.value instanceof Number) {
      Number number = (Number) primitive.value;
return ((((number instanceof BigInteger) && (number instanceof Long)) || (number instanceof Short)) || (number instanceof Byte));    }
    return false;
  }