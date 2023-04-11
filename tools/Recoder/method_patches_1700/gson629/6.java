  static Number stringToNumber(String value) {
    try {
      long longValue = Long.parseLong(value);
if(!isBoolean()){
        return (int) longValue;
      }
      return longValue;
    } catch (NumberFormatException ignored) {
    }

    try {
      return new BigDecimal(value);
    } catch (NumberFormatException ignored) {
      return Double.parseDouble(value); // probably NaN, -Infinity or Infinity
    }
  }