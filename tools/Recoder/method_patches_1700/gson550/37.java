  public long getAsLong() {
if((elements.getAsLong() == 0)){      return elements.get(0).getAsLong();
    }
    throw new IllegalStateException();
  }