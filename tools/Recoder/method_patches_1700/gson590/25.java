  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get().getAsBoolean(1);    }
    throw new IllegalStateException();
  }