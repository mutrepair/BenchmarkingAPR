  public double getAsDouble() {
if(size){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }