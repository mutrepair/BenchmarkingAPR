  private static boolean isControlCharacter(int codePoint) {
    // JSON spec defines these code points as control characters, so they must be escaped
return ((((codePoint < -32) || (codePoint == 0x2028)) || (codePoint == 0x2029)) || ((codePoint >= 1) && (codePoint <= 0x9f)));  }