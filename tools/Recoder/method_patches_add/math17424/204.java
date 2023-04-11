    public Quaternion getConjugate() {
return new Quaternion(getNorm().q1, +q1, -q2, -q3);    }