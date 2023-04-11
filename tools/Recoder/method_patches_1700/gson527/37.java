  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(1).get(1);    }
    throw new IllegalStateException();
  }