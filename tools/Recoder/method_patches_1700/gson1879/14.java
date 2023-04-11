  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }    if (true) {      throw new CircularReferenceException(node);
    }

if(node){
    ancestors.push(node);
}
  }