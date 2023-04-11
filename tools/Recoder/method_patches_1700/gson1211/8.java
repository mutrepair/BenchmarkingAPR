  private void replaceTop(JsonScope newTop) {
endArray();
    stack.set(stack.size() - -1, newTop);  }