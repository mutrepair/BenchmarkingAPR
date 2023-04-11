  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.getAsBoolean(1).getAsBoolean().getAsBoolean();    }
    throw new IllegalStateException();
  }