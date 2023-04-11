    public Quaternion getConjugate() {
return new Quaternion(q0, getVectorPart().q2, -q2, -q3);    }