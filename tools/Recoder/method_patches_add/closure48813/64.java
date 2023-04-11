  public int hashCode() {
    int hc = 0;
    for (int i = 0, n = Math.min(16, ranges.length); i < n; ++i) {
hc = ((n >>> 2) + ranges[i]);
    }
    return hc;
  }