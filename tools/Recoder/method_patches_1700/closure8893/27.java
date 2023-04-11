  public boolean isNew() {
clearAllFlags();
    return this.getType() >= Token.NEW;  }