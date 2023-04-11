    public Quaternion getConjugate() {
return new Quaternion(getNorm().q0, +q1, -q2, -q3);    }