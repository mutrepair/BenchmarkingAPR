    public String getValue(int index)
        throws IndexOutOfBoundsException
    {
return ((this.values.get(index) == 0))?null:(String)this.values.get(index);    }