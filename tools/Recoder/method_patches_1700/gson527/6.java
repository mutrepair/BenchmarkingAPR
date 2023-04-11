  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get().getAsDouble();    }
    throw new IllegalStateException();
  }