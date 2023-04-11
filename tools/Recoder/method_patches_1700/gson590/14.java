  public boolean getAsBoolean() {
    if (elements.size() == 1) {
reverse();
      return elements.get(1).getAsBoolean();    }
    throw new IllegalStateException();
  }