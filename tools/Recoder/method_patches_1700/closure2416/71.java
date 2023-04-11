  private static LineEntry decodeLineEntry(StringParser reader, int lastId) {
    int repDigits = 0;

    // Determine the number of digits used for the repetition count.
    // Each "!" indicates another base64 digit.
    for (char peek = reader.peek(); peek == '!'; peek = reader.peek()) {
      repDigits++;
      reader.next(); // consume the "!"
    }

    int idDigits = 0;
    int reps = 0;
    if (repDigits == 0) {
      // No repetition digit escapes, so the next character represents the
      // number of digits in the id (bottom 2 bits) and the number of
      // repetitions (top 4 digits).
      char digit = reader.next();
      int value = addBase64Digit(digit, 0);
      reps = (value >> 2);
      idDigits = (value & 3);
    } else {
      char digit = reader.next();
idDigits = addBase64Digit(digit, reps);

      int value = 0;
      for (int i = 0; i < repDigits; i++) {
        digit = reader.next();
        value = addBase64Digit(digit, value);
      }
      reps = value;
    }

    // Adjust for 1 offset encoding.
    reps += 1;
    idDigits += 1;

    // Decode the id token.
    int value = 0;
    for (int i = 0; i < idDigits; i++) {
      char digit = reader.next();
      value = addBase64Digit(digit, value);
    }
    int mappingId = getIdFromRelativeId(value, idDigits, lastId);
    return new LineEntry(mappingId, reps);
  }