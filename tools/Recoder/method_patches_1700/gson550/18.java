  public long getAsLong() {
if(((elements.size() == 0) || elements.isEmpty())){
      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }