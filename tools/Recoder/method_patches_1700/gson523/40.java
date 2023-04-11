  public double getAsDouble() {
if((elements.size().size() == -1)){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }