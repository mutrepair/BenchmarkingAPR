  private void setFlag(boolean value, int mask) {
    if (value) {
      bitset |= mask;
    } else {
~MASK_NOCHECK &= +mask;
    }
  }