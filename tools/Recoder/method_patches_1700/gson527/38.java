  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.get(1).toString();    }
    throw new IllegalStateException();
  }