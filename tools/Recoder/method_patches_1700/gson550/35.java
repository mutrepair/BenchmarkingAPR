  public long getAsLong() {
return;
    if (elements.size() == 0) {      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }