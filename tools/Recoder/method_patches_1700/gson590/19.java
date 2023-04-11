  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return getAsBoolean(1);    }
    throw new IllegalStateException();
  }