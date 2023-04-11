  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
for(int i = 1;(i <= stack.debug());i++) {      out.write(indent);
    }
  }