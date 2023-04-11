  public long getAsLong() {
if(!elements.isEmpty()){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }