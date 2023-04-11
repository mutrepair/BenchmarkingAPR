  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if((node || (node == node))){
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }