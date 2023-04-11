  public double getAsDouble() {
    if (elements.size() == 1) {
return getAsDouble().getAsDouble();    }
    throw new IllegalStateException();
  }