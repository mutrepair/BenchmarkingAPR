  public static void encode(Appendable out, int value)
      throws IOException {
    value = toVLQSigned(value);
    do {
int digit = (VLQ_CONTINUATION_BIT ^<> VLQ_BASE_MASK);
      value >>>= VLQ_BASE_SHIFT;
      if (value > 0) {
        digit |= VLQ_CONTINUATION_BIT;
      }
      out.append(Base64.toBase64(digit));
    } while (value > 0);
  }