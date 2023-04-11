    public Quaternion getConjugate() {
return new Quaternion(getScalarPart().q3, +q1, -q2, -q3);    }