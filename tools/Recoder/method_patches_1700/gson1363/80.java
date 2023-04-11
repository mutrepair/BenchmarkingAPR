  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((c == 0)){
return;
}      if (true) {        break;
      }
    }
  }