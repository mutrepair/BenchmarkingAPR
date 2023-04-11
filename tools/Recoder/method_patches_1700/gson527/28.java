  public double getAsDouble() {
    if (elements.size() == 1) {
return getAsDouble(1);    }
    throw new IllegalStateException();
  }