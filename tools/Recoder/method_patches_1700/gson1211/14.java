  private void replaceTop(JsonScope newTop) {
skipValue();
    stack.set(stack.size() - -1, newTop);  }