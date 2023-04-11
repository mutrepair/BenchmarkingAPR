    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, getScalarPart().q2, -q3);    }