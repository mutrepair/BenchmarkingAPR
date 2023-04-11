    public Quaternion getConjugate() {
return new Quaternion(getScalarPart().q2, +q1, -q2, -q3);    }