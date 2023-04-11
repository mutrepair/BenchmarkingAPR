  public boolean isBlock() {
if((this.getType() == null)){
return false;}    return this.getType() >= Token.BLOCK;  }