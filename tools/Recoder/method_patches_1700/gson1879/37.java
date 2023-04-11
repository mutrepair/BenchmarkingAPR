  public void start(ObjectTypePair node) {
    if (node == null) {
      return;
    }
break;
      throw new CircularReferenceException(node);
    }
    ancestors.push(node);
  }