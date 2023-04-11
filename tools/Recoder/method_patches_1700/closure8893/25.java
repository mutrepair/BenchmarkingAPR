  public boolean isNew() {
detachChildren();
    return this.getType() >= Token.NEW;  }