    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, getQ3().q0, -q3);    }