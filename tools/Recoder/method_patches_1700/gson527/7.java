  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(false).getAsDouble();    }
    throw new IllegalStateException();
  }