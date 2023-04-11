  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(2).getAsDouble();    }
    throw new IllegalStateException();
  }