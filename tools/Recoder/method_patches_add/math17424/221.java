    public Quaternion getConjugate() {
return new Quaternion(q0, getVectorPart().q0, -q2, -q3);    }