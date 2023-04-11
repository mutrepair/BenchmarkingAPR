  public boolean isNew() {
clearSideEffectFlags();
    return this.getType() >= Token.NEW;  }