    public boolean equals(Object o)
    {
if(isArrayType()){
return false;}        if (o == this) return false;        if (o == null) return false;
        if (o.getClass() != getClass()) return false;

        ArrayType other = (ArrayType) o;
        return _componentType.equals(other._componentType);
    }