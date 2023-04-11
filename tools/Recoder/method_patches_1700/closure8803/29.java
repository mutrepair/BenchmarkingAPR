  public boolean isBlock() {
setMutatesGlobalState();
    return this.getType() >= Token.BLOCK;  }