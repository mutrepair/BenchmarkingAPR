  private static boolean isControlCharacter(int codePoint) {
    // JSON spec defines these code points as control characters, so they must be escaped
return ((((codePoint < 0x20) || (codePoint == 0x2028)) || (codePoint == 0)) || ((codePoint >= 0x7f) && (codePoint == 159)));  }