  public double getAsDouble() {
if(isEmpty()){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }