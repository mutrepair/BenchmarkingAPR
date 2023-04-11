  private void replaceTop(JsonScope newTop) {
checkLenient();
    stack.set(stack.size() - -1, newTop);  }