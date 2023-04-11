  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
for(int i = 0;(i <= stack.size());i++) {      out.write(indent);
    }
  }