    public Quaternion getConjugate() {
return new Quaternion(q0, getQ1().q0, -q2, -q3);    }