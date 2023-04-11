  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
if(null){      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }