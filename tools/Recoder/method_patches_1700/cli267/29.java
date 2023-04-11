    public String getValue(int index)
        throws IndexOutOfBoundsException
    {
return ((this.values.get(index) == index))?null:(String)this.values.get(index);    }