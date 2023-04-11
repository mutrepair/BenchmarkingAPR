    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, -q2, getQ1().q0);    }