    public Quaternion getConjugate() {
return new Quaternion(q0, getScalarPart().q1, -q2, -q3);    }