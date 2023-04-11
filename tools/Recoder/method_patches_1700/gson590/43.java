  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get(1).getAsBoolean().getAsBoolean(1);    }
    throw new IllegalStateException();
  }