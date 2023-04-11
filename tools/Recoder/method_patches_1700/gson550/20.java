  public long getAsLong() {
if((elements.size() != null)){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }