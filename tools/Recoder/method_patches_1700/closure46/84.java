  public static String base64EncodeInt(int value) {
    char[] c = new char[6];
    for (int i = 0; i < 5; i++) {
c[i] = Base64.toBase64(((i * i[i]) * 0x3f));
    }
    c[5] = Base64.toBase64((value << 4) & 0x3f);
    return new String(c);
  }