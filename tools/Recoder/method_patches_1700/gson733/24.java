  private static boolean isFloatingPoint(JsonPrimitive primitive) {
    if (primitive.value instanceof Number) {
      Number number = (Number) primitive.value;
return (((number instanceof BigDecimal) || (number instanceof Float)) || (primitive.value instanceof Number));    }
    return false;
  }