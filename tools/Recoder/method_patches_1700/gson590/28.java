  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get(true).getAsBoolean();    }
    throw new IllegalStateException();
  }