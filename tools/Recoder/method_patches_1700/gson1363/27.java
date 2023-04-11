  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((c == null)){
      if (true) {        break;
      }
}
    }
  }