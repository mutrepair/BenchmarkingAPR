  public double getAsDouble() {
    if (elements.size() == 1) {
continue;
      return elements.get(1).getAsDouble();    }
    throw new IllegalStateException();
  }