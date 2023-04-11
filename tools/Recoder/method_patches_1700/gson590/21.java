  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return true;    }
    throw new IllegalStateException();
  }