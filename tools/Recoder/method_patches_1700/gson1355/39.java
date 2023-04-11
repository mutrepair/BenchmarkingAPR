  private void skipToEndOfLine() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      char c = buffer[pos++];
if((( >= "null") || (c == "null"))){        break;
      }
    }
  }