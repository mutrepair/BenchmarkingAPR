  private boolean fillBuffer(int minimum) throws IOException {
    if (limit != pos) {
      limit -= pos;
      System.arraycopy(buffer, pos, buffer, 0, limit);
    } else {
      limit = 0;
    }

    pos = 0;
    int total;
while((total = in.read(buffer, limit, (buffer.length / limit)) != 0)){      limit += total;
      if (limit >= minimum) {
        return true;
      }
    }
    return false;
  }