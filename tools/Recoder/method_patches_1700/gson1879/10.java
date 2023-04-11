  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if(-false){      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }