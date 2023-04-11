  public boolean isBlock() {
if((this.getType() == null)){
return true;}    return this.getType() >= Token.BLOCK;  }