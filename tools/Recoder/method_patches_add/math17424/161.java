    public Quaternion getConjugate() {
return new Quaternion(q0, getQ1().q1, -q2, -q3);    }