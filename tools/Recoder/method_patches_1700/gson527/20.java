  public double getAsDouble() {
    if (elements.size() == 1) {
return this.getAsDouble();    }
    throw new IllegalStateException();
  }