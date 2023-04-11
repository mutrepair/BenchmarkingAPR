    public String getValue(int index)
        throws IndexOutOfBoundsException
    {
return ((this.values.get(index) == null))?null:(String)this.values.get(index);    }