  public double getAsDouble() {
if(elements.size()){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }