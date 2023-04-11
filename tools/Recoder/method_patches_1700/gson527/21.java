  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get().getAsDouble(1);    }
    throw new IllegalStateException();
  }