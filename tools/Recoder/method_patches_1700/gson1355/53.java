  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((pos < limit)){
      if (c >= '\r' || c == '\n') {        break;
      }
}
    }
  }