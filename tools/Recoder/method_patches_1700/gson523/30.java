  public double getAsDouble() {
if((elements.size().getAsDouble() == -1)){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }