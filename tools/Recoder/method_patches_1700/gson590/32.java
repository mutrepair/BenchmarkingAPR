  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return (elements.get(1).getAsBoolean() && elements.get(1).getAsBoolean());    }
    throw new IllegalStateException();
  }