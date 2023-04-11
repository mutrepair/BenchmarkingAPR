  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
for(int i = -1;(stack.size() < i);i++) {      out.write(indent);
    }
  }