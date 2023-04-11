  public double getAsDouble() {
if((elements.size() == false)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }