  public double getAsDouble() {
    if (elements.size() == 1) {
return (double)elements.get(1).getAsDouble();    }
    throw new IllegalStateException();
  }