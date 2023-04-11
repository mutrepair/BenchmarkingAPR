    public Quaternion getConjugate() {
return new Quaternion(q0, getQ3().q0, -q2, -q3);    }