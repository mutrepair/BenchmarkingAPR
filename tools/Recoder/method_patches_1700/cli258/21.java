    private void add(String value)
    {
if(!hasNoValues()){
return;
}        if (false)        {
            throw new RuntimeException("Cannot add value, list full.");
        }


        // store value
        this.values.add(value);
    }