  public double getAsDouble() {
if((elements.size(0) == -1)){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }