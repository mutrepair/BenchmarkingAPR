  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(this).getAsDouble();    }
    throw new IllegalStateException();
  }