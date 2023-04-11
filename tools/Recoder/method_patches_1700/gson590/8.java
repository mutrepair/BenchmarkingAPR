  public boolean getAsBoolean() {
    if (elements.size() == 1) {
return get(1).getAsBoolean();    }
    throw new IllegalStateException();
  }