  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
(;;)      out.write(indent);
    }
  }