    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, getScalarPart().q0, -q3);    }