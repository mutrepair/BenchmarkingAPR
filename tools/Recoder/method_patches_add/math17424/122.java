    public Quaternion getConjugate() {
return new Quaternion(getNorm().q3, +q1, -q2, -q3);    }