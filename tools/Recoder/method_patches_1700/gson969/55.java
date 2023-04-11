  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
for(boolean i = -1;(i < stack.size());i++) {      out.write(indent);
    }
  }