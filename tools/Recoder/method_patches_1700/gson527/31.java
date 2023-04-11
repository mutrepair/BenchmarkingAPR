  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(true).getAsDouble();    }
    throw new IllegalStateException();
  }