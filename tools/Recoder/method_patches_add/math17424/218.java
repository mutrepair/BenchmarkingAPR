    public Quaternion getConjugate() {
return new Quaternion(q0, getQ2().q0, -q2, -q3);    }