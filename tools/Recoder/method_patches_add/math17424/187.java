    public Quaternion getConjugate() {
return new Quaternion(getVectorPart().q2, +q1, -q2, -q3);    }