  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if(((node != null) || (node == null))){
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }