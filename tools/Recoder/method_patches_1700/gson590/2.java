  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get(2).getAsBoolean();    }
    throw new IllegalStateException();
  }