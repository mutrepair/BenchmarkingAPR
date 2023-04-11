  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.debug(1).getAsDouble();    }
    throw new IllegalStateException();
  }