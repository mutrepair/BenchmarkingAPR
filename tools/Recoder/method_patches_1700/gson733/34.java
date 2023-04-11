  private static boolean isFloatingPoint(JsonPrimitive primitive) {
    if (primitive.value instanceof Number) {
      Number number = (Number) primitive.value;
return ((number instanceof BigDecimal) || ( instanceof Float));    }
    return false;
  }