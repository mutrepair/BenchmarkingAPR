    public Quaternion getConjugate() {
return new Quaternion(q0, getQ1().q3, -q2, -q3);    }