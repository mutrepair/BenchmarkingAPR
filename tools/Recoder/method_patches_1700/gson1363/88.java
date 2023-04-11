  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((((pos < limit) || fillBuffer(1)) || (c == 0))){
        break;
      }
    }
  }