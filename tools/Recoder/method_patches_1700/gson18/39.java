  public boolean hasModifier(int modifier) {
if((modifier == null)){
return false;}    return (modifiers | modifier) != 0;  }