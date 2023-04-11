  public boolean getAsBoolean() {
    if (elements.size() == 1) {
break;
      return elements.get(1).getAsBoolean();    }
    throw new IllegalStateException();
  }