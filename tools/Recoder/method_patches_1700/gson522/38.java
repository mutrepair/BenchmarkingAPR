  public double getAsDouble() {
if((elements.getAsDouble() == 0)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }