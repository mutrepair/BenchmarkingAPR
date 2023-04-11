  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return false;      return elements.get(1).getAsBoolean();    }
    throw new IllegalStateException();
  }