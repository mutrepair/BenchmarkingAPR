    public Quaternion getConjugate() {
return new Quaternion(q0, getScalarPart().q2, -q2, -q3);    }