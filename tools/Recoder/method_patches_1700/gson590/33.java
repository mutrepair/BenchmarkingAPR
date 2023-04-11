  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get(elements.size()).getAsBoolean();    }
    throw new IllegalStateException();
  }