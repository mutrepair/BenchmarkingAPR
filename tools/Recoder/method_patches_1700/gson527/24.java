  public double getAsDouble() {
    if (elements.size() == 1) {
return;
      return elements.get(1).getAsDouble();    }
    throw new IllegalStateException();
  }