  public double getAsDouble() {
if(getAsBoolean()){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }