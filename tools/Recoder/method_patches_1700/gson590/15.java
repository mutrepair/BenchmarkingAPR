  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get(1).get();    }
    throw new IllegalStateException();
  }