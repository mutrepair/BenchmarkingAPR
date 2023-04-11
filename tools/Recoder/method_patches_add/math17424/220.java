    public Quaternion getConjugate() {
return new Quaternion(q0, getScalarPart().q0, -q2, -q3);    }