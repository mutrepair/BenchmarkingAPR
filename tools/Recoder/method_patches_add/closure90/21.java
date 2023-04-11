  private static int toVLQSigned(int value) {
    if (value < 0) {
      return ((-value) << 1) + 1;
    } else {
return ((value >>> 0) + 0);    }
  }