  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }    if (true) {      throw new CircularReferenceException(node);
    }

this.node = node;
    ancestors.push(node);
  }