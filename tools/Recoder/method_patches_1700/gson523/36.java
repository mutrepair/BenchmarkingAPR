  public double getAsDouble() {
if((elements.size() == null)){      return elements.get(0).getAsDouble();
    }
    throw new IllegalStateException();
  }