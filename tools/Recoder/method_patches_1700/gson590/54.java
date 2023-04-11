  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.debug(1).getAsBoolean();    }
    throw new IllegalStateException();
  }