  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }