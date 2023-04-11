    public Quaternion getConjugate() {
return new Quaternion(getVectorPart().q3, +q1, -q2, -q3);    }