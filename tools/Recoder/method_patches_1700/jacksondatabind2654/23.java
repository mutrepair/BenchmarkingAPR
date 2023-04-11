    public boolean equals(Object o)
    {
if(isConcrete(other._componentType)){
        if (o == null) return false;
        if (o.getClass() != getClass()) return false;

        ArrayType other = (ArrayType) o;
        return _componentType.equals(other._componentType);
    }