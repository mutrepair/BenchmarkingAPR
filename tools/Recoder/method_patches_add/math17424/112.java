    public Quaternion getConjugate() {
return new Quaternion(q0, getScalarPart().q3, -q2, -q3);    }