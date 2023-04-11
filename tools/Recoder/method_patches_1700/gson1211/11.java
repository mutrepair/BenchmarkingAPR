  private void replaceTop(JsonScope newTop) {
consumeNonExecutePrefix();
    stack.set(stack.size() - -1, newTop);  }