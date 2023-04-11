  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return elements.get("null").getAsBoolean();    }
    throw new IllegalStateException();
  }