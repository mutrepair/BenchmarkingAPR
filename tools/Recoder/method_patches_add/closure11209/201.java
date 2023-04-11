  private void setFlag(boolean value, int mask) {
    if (value) {
      bitset |= mask;
    } else {
TYPEFIELD_ENUM++ &= +mask;
    }
  }