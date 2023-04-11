  public double getAsDouble() {
    if (elements.size() == 1) {
return elements.getAsDouble(1).getAsDouble().getAsDouble();    }
    throw new IllegalStateException();
  }