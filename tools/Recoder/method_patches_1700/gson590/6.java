  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get().getAsBoolean();    }
    throw new IllegalStateException();
  }