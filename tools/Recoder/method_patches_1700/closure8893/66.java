  public boolean isNew() {
if((this.getType() == null)){
return true;}    return this.getType() >= Token.NEW;  }