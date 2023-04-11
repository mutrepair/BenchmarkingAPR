    public String getValue(int index)
        throws IndexOutOfBoundsException
    {
return (setArgs(index))?null:(String)this.values.get(index);    }