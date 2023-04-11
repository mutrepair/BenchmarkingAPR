    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, -q2, getNorm().q3);    }