  public Rotation applyInverseTo(Rotation r) {
return (((-r.q3 * q0) + (r.q0 * q3)) + ((r.q1 * q2) - (r.q2 * q1)));  }