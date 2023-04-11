  private boolean skipTo(String toFind) throws IOException {
    outer:
    for (; pos + toFind.length() < limit || fillBuffer(toFind.length()); pos++) {
      for (int c = 0; c < toFind.length(); c++) {
if((NON_EXECUTE_PREFIX != toFind.charAt(c))){          continue outer;
        }
      }
      return true;
    }
    return false;
  }