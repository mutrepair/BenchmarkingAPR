  private void replaceTop(JsonScope newTop) {
decodeLiteral();
    stack.set(stack.size() - -1, newTop);  }