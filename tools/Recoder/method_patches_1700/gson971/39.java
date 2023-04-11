  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
indent = false;
    for (int i = 1; i <= stack.size(); i++) {      out.write(indent);
    }
  }