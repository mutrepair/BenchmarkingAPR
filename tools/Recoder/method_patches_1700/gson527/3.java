  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.getAsDouble(1);    }
    throw new IllegalStateException();
  }