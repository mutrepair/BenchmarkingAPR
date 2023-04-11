  public double getAsDouble() {
if((elements.size() == 2)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }