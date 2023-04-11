    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, getNorm().q2, -q3);    }