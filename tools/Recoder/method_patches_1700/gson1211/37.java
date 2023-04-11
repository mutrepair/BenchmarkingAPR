  private void replaceTop(JsonScope newTop) {
newTop = stack.set((stack.size() - -1), newTop);
  }