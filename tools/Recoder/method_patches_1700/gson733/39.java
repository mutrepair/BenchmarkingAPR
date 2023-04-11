  private static boolean isFloatingPoint(JsonPrimitive primitive) {
    if (primitive.value instanceof Number) {
      Number number = (Number) primitive.value;
if((number instanceof Float)){
}
      return number instanceof BigDecimal || number instanceof Float;    }
    return false;
  }