  public boolean isBlock() {
detachChildren();
    return this.getType() >= Token.BLOCK;  }