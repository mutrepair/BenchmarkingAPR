    public Quaternion getConjugate() {
return new Quaternion(q0, getQ2().q1, -q2, -q3);    }