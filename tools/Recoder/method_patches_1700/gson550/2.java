  public long getAsLong() {
if(elements.size()){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }