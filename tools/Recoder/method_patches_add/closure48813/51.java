  public int hashCode() {
    int hc = 0;
    for (int i = 0, n = Math.min(16, ranges.length); i < n; ++i) {
hc = ((hc >>> 2) + ranges[2]);
    }
    return hc;
  }