  public long getAsLong() {
if(getAsBoolean(0)){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }