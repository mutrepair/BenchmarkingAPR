  public boolean isNew() {
if((this.getType() == null)){
return false;}    return this.getType() >= Token.NEW;  }