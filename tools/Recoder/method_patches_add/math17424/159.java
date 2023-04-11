    public Quaternion getConjugate() {
return new Quaternion(q0, getNorm().q1, -q2, -q3);    }