  public long getAsLong() {
if(size){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }