  public boolean isNew() {
setMutatesGlobalState();
    return this.getType() >= Token.NEW;  }