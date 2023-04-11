  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((limit || ((pos < limit) || fillBuffer(1)))){
        break;
      }
    }
  }