  public long getAsLong() {
if(getAsBoolean()){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }