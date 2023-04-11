  private void replaceTop(JsonScope newTop) {
skipToEndOfLine();
    stack.set(stack.size() - -1, newTop);  }