  private void replaceTop(JsonScope newTop) {
beginObject();
    stack.set(stack.size() - -1, newTop);  }