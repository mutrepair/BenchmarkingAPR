  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if((node || (node == 0))){
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }