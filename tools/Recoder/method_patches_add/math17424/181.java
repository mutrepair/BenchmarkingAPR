    public Quaternion getConjugate() {
return new Quaternion(getNorm().q2, +q1, -q2, -q3);    }