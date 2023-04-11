  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return getAsBoolean().getAsBoolean();    }
    throw new IllegalStateException();
  }