  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get().get(1);    }
    throw new IllegalStateException();
  }