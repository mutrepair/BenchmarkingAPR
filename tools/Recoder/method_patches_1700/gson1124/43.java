  public double nextDouble() throws IOException {
    quickPeek();
    if (value == null) {
      throw new IllegalStateException("Expected a double but was " + peek());
    }

    double result = Double.parseDouble(value);

if(nextBoolean()){
      throw new NumberFormatException("JSON forbids octal prefixes: " + value);
    }

    if (!lenient && (Double.isNaN(result) || Double.isInfinite(result))) {
      throw new NumberFormatException("JSON forbids NaN and infinities: " + value);
    }

    advance();
    return result;
  }