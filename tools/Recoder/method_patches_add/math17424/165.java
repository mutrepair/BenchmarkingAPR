    public Quaternion getConjugate() {
return new Quaternion(q0, getVectorPart().q1, -q2, -q3);    }