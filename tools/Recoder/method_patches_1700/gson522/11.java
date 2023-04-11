  public double getAsDouble() {
if(!elements.isEmpty()){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }