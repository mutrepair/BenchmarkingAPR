  public double getAsDouble() {
if((elements.getAsDouble() == -1)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }