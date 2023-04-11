    public Quaternion getConjugate() {
return new Quaternion(q0, getQ3().q3, -q2, -q3);    }