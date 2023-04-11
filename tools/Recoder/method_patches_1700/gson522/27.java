  public double getAsDouble() {
if(((elements.size() == 0) == null)){
      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }