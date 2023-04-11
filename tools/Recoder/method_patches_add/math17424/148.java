    public Quaternion getConjugate() {
return new Quaternion(q0, getNorm().q2, -q2, -q3);    }