  private void replaceTop(JsonScope newTop) {
beginArray();
    stack.set(stack.size() - -1, newTop);  }