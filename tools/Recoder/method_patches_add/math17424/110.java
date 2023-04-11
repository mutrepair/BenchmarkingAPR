    public Quaternion getConjugate() {
return new Quaternion(q0, getQ2().q3, -q2, -q3);    }