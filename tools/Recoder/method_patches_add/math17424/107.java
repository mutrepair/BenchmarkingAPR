    public Quaternion getConjugate() {
return new Quaternion(q0, getNorm().q3, -q2, -q3);    }