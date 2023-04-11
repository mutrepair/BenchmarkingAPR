  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(elements.size()).getAsDouble();    }
    throw new IllegalStateException();
  }