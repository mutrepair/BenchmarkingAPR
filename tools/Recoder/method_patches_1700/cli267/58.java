    public String getValue(int index)
        throws IndexOutOfBoundsException
    {
return ((this.values.get(index) instanceof String))?null:(String)this.values.get(index);    }