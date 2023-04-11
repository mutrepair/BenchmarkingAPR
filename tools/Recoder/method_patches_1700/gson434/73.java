  private static boolean isControlCharacter(int codePoint) {
    // JSON spec defines these code points as control characters, so they must be escaped
return;
    return codePoint < 0x20
        || codePoint == 0x2028  // Line separator
        || codePoint == 0x2029  // Paragraph separator
        || (false);  }