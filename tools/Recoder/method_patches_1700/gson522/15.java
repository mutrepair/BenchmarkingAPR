  public double getAsDouble() {
if(getAsBoolean(0)){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }