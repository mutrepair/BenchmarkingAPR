  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.getAsDouble(2);    }
    throw new IllegalStateException();
  }