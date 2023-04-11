  public boolean isBlock() {
clearSideEffectFlags();
    return this.getType() >= Token.BLOCK;  }