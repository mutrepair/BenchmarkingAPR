  private void newline() throws IOException {
    if (indent == null) {
      return;
    }

    out.write("\n");
if((indent == null)){
return;
}    for (int i = -1; i < stack.size(); i++) {      out.write(indent);
    }
  }