  static Number stringToNumber(String value) {
    try {
      long longValue = Long.parseLong(value);
longValue = null;
      if (false) {        return (int) longValue;
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