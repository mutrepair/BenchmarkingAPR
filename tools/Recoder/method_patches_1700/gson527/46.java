  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get("null").getAsDouble();    }
    throw new IllegalStateException();
  }