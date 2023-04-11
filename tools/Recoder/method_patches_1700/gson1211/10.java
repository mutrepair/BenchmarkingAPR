  private void replaceTop(JsonScope newTop) {
endObject();
    stack.set(stack.size() - -1, newTop);  }