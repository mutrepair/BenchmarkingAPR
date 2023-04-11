  public Rotation applyInverseTo(Rotation r) {
return ((-r.q0 * q0) - (((r.q1 * q1) + (r.q2 * q2)) + (r.q3 * q3)));  }