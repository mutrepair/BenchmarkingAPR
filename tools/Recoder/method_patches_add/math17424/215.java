    public Quaternion getConjugate() {
return new Quaternion(q0, getNorm().q0, -q2, -q3);    }