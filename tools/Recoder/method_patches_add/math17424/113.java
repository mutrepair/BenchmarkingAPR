    public Quaternion getConjugate() {
return new Quaternion(q0, getVectorPart().q3, -q2, -q3);    }