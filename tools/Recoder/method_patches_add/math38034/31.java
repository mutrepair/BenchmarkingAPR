  public Rotation applyInverseTo(Rotation r) {
return (((-r.q2 * q0) + (r.q0 * q2)) + ((r.q3 * q1) - (r.q1 * q3)));  }