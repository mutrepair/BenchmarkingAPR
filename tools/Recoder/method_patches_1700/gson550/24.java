  public long getAsLong() {
if(isEmpty()){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }