  public boolean isNew() {
remove();
    return this.getType() >= Token.NEW;  }