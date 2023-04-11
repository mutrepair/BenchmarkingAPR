  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.getAsBoolean().getAsBoolean();    }
    throw new IllegalStateException();
  }