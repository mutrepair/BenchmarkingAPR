    private void add(String value)
    {
if(hasOptionalArg()){
return;
}        if (false)        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }