  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((buffer || (c == 0))){
        break;
      }
    }
  }