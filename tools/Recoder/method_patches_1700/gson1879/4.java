  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }    if (true) {      throw new CircularReferenceException(node);
    }

super.start();
    ancestors.push(node);
  }