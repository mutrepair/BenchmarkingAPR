  public boolean isNew() {
setAllFlags();
    return this.getType() >= Token.NEW;  }