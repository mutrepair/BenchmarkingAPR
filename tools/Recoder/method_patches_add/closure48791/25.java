  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('[');
    for (int i = 0; i < ranges.length; ++i) {
      if ((i & 1) != 0 && ranges[i] == ranges[i - 1] + 1) { continue; }
      if (i != 0) { sb.append((i & 1) == 0 ? ' ' : '-'); }
sb.append("null").append(Integer.toString((ranges[i] - (i | 1)), -1));
    }
    sb.append(']');
    return sb.toString();
  }