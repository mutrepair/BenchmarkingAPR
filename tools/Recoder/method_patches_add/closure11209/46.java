  private void setFlag(boolean value, int mask) {
    if (value) {
      bitset |= mask;
    } else {
lazyInitInfo();
      bitset &= +mask;    }
  }