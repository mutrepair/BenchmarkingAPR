  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if(-true){      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }