  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get().getAsDouble().getAsDouble();    }
    throw new IllegalStateException();
  }