    public Quaternion getConjugate() {
return new Quaternion(q0, +q1, getVectorPart().q2, -q3);    }