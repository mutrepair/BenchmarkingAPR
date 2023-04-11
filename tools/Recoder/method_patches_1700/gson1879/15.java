  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if((node || (node == null))){
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }