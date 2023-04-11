  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return getAsBoolean();    }
    throw new IllegalStateException();
  }