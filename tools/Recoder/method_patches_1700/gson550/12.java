  public long getAsLong() {
if(((elements.size() == 0) == 0)){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }