  public double getAsDouble() {
if((elements.size() > 0)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }